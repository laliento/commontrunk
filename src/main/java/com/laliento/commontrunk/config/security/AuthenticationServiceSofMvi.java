package com.laliento.commontrunk.config.security;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.laliento.commontrunk.entity.ParametroGeneral;
import com.laliento.commontrunk.entity.Usuario;
import com.laliento.commontrunk.repository.LogginAttemptsRepository;
import com.laliento.commontrunk.repository.ParametroGeneralRepository;
import com.laliento.commontrunk.repository.UsuarioRepository;
import com.laliento.commontrunk.util.TimeUtils;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Component
@Transactional
public class AuthenticationServiceSofMvi implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private LogginAttemptsRepository logginAttemptsRepository;
	@Autowired
	private ParametroGeneralRepository parametroGeneralRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		//si no existe el usuario se lanza InternalAuthenticationServiceException que es cachada
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+usuario.getPerfil().getDescripcion());
		Boolean enabled = usuario.isEnable();
		Boolean accountNonExpired = true;
		Boolean credentialsNonExpired = true;
		Boolean accountNonLocked = !evitaFuerzaBruta(usuario);
		User user = new User(usuario.getUsername(), 
				usuario.getPassword()+"|"+usuario.getSalt(), 
				enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,
				Arrays.asList(authority));
		UserDetails userDetails = (UserDetails)user;
		return userDetails;
	}
	private Boolean evitaFuerzaBruta(Usuario usuario) {
		ParametroGeneral parametroGeneral = parametroGeneralRepository.findByClave("intentosPermitidosLoggin");
		Integer intentosPermitidosLoggin = parametroGeneral ==null?5 : Integer.valueOf(parametroGeneral.getValor());
		parametroGeneral = parametroGeneralRepository.findByClave("minutosCuentaBloqueada");
		Integer minutosCuentaBloqueada = parametroGeneral ==null?30 : Integer.valueOf(parametroGeneral.getValor());
		Date tiempoAtras = TimeUtils.calculaTiempoatras(minutosCuentaBloqueada);
		Integer numeroDeIntentos = logginAttemptsRepository.countLogginAttemptsByUsuarioAndTiempoBetween(usuario,tiempoAtras,new Date()).intValue();
		if(superaNumeroIntentos(numeroDeIntentos,intentosPermitidosLoggin))
			return true;
		return false;
	}
	private boolean superaNumeroIntentos(Integer numeroDeIntentos,Integer intentosPermitidosLoggin) {
		return numeroDeIntentos>=intentosPermitidosLoggin;
	}
	
}
