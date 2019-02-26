package com.laliento.commontrunk.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.laliento.commontrunk.entity.EstadoUsuario;
import com.laliento.commontrunk.entity.Perfil;
import com.laliento.commontrunk.entity.Usuario;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Component
public class AuthenticationServiceSofMvi implements UserDetailsService{

//	@Autowired
//	private UsuarioDao usuarioDao;
//	@Autowired
//	private LogginAttemptsDao logginAttemptsDao;
//	@Autowired
//	private ParametroGeneralDao parametroGeneralDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Usuario usuario = usuarioDao.findByUserName(username);
		Usuario usuario = new Usuario("laliento", "Eduardo", "C", "Z", "eduardo.cz.mac@gmail.com", 
				"cdaee49a44ce698a5740a624fd84d4f42c841f8360b8e19c68439bcb845637f5b42dbf4e28f0771367a76f63a9f0041073506a84b8cae3fe0fd209500e59b5c3", 
				"ee145902a298f43bb90bb8a312efe20ad3d9997a3c390b1ba8ca19cea3fe242fad56510f2415c0b1a536f8dc0cf0638da769a9d888ef992b70cca62f3bf98cd2" + 
				"", new EstadoUsuario(1), new Perfil(1,"ADMIN"), true);
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
//		Integer minutosCuentaBloqueada = parametroGeneralDao.findByClaveReturnInteger("minutosCuentaBloqueada");
//		Integer intentosPermitidosLoggin = parametroGeneralDao.findByClaveReturnInteger("intentosPermitidosLoggin");
//		Integer numeroDeIntentos = logginAttemptsDao.findNumeroIntentosByUserAndMinutos(usuario,minutosCuentaBloqueada);
		Integer minutosCuentaBloqueada = 30;
		Integer intentosPermitidosLoggin = 5;
		Integer numeroDeIntentos = 1;
			if(superaNumeroIntentos(numeroDeIntentos,intentosPermitidosLoggin))
				return true;
		return false;
	}
	private boolean superaNumeroIntentos(Integer numeroDeIntentos,Integer intentosPermitidosLoggin) {
		return numeroDeIntentos>=intentosPermitidosLoggin;
	}
	
}
