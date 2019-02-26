package com.laliento.commontrunk.view.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.laliento.commontrunk.entity.CatMenu;
import com.laliento.commontrunk.entity.RelPerfilMenu;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Named
@lombok.Getter
@lombok.Setter
public class LoginView extends BackingBean implements ViewMethodDefault{
	private static final long serialVersionUID = 1L;
//	@Autowired
//	private RelPerfilMenuDao relPerfilMenuDao;
	private List<RelPerfilMenu> lstRelPerfilMenu;
	@Override
	public String irPagina() {
		String pagina =getPageByUser(); 
		if (pagina.contains("login"))
			return null;
		else{
			lstRelPerfilMenu= null;//relPerfilMenuDao.findByUsuario(getSessionUser());
			for (RelPerfilMenu relPerfilMenu : lstRelPerfilMenu) {
				Set<CatMenu> subMenus = new HashSet<CatMenu>();
//				for (RelPerfilMenu relPerfilMenuSub : relPerfilMenuDao.findByUsuarioAndMenuPadre(getSessionUser(),relPerfilMenu)) {
//					subMenus.add(relPerfilMenuSub.getCatMenu());
//				}
				relPerfilMenu.getCatMenu().setSubMenus(subMenus);
			}
			return pagina;
		}
	}
	@Override
	public void initPage() {}
	@Override
	public void loadElements() {}
	@Override
	public void cleanPage() {}
}
