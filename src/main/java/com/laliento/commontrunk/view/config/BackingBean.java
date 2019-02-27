package com.laliento.commontrunk.view.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Properties;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.internal.sessions.PropertiesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.laliento.commontrunk.entity.Usuario;
import com.laliento.commontrunk.repository.UsuarioRepository;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Component
@Transactional
public class BackingBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static Logger log = LogManager.getLogger();
	private Authentication auth;
	private boolean flagErrorMsg;
	@Autowired
	private UsuarioRepository usuarioRepository;
	public BackingBean() {
		setFlagErrorMsg(false);	
	}
	public Usuario getSessionUser()
	{	
//		getPageByUser();
		auth = getAuthentication();
		if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") ==null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user",usuarioRepository.findByUsername(auth.getName()));
		}
		return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	}
	public Usuario getForceSessionuser() {
		auth = getAuthentication();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user",usuarioRepository.findByUsername(auth.getName()));
		return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	}
	@SuppressWarnings("unchecked")
	public String getPageByUser(){
		String pagina="login.xhtml?faces-redirect=true";
//		if(auth == null) // se comenta para recuperar el role desde la p�gina de login si es que ya estaba logeado se redireccione
		auth = getAuthentication();
		if(auth.isAuthenticated() && auth.getName() != "anonymousUser"){
			Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
			for (SimpleGrantedAuthority simpleGrantedAuthority : authorities) {//pregunta por rol cual es su p�gina de inicio
				if(simpleGrantedAuthority.toString().equals("ROLE_ADMIN")){
					pagina="/pages/admin/admin.xhtml?faces-redirect=true";
				}else if(simpleGrantedAuthority.toString().equals("ROLE_LALO")){
					pagina="/pages/lalo/lalo.xhtml?faces-redirect=true";
				}else if(simpleGrantedAuthority.toString().equals("ROLE_USER")){
					pagina="/pages/user/user.xhtml?faces-redirect=true";
				}
				log.info("Start session user '{}' and ROLE '{}'",auth.getName(),simpleGrantedAuthority.toString());
			}
		}
		return pagina;
	}
	public Authentication getAuthentication()
	{
		auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
	public static Properties loadProperties(String propertiesFileName) {
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = PropertiesHandler.class
					.getResourceAsStream(propertiesFileName);
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return properties;
	}
	public void limpiarMemoria() {
		try {
			log.info("Start GC.");
			Runtime.getRuntime().gc();
			log.info("End GC.");
		} catch (Exception e) {
			log.error("Fail into clean memory. " + e);
		}
	}
	public boolean isFlagErrorMsg() {
		return flagErrorMsg;
	}
	public void setFlagErrorMsg(boolean flagErrorMsg) {
		this.flagErrorMsg = flagErrorMsg;
	}
}
