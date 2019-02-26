package com.laliento.commontrunk.config.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.laliento.commontrunk.entity.LogginAttempts;
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
public class UserNameCachingAuthenticationFailureHandlerSoftMvi extends SimpleUrlAuthenticationFailureHandler {
	/*
	 * Variabes independientes 
	 */
	private String usernameParameter = "app_username";
	private String passwordParameter = "app_password";
	private boolean postOnly = true;
	/*
	 * Inject
	 */
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private LogginAttemptsRepository logginAttemptsRepository;
	@Autowired
	private ParametroGeneralRepository parametroGeneralRepository; 
	public UserNameCachingAuthenticationFailureHandlerSoftMvi(){super("/login.xhtml?error=BadCredentials");}
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {
    	if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Método de autentificación no soportado: " + request.getMethod());
		}
    	String mensajeRegreso="";
    	if(exception instanceof BadCredentialsException){
    		mensajeRegreso = evitaFuerzaBruta(request);
    	}else if (exception instanceof LockedException){
    		mensajeRegreso = "Su cuenta se encuentra desabilitada temporalmente.";
    	}else if(exception instanceof UsernameNotFoundException || exception instanceof InternalAuthenticationServiceException){
    		mensajeRegreso = "Usuario no registrado.";
    	}else if (exception instanceof AccountStatusException){
    		mensajeRegreso = "Su cuenta se encuentra desabilitada.";
    	}
    	AuthenticationException authenticationException = new AuthenticationException(mensajeRegreso) {
			private static final long serialVersionUID = 1L;};
    	super.onAuthenticationFailure(request, response, authenticationException);
    }
    private String evitaFuerzaBruta(HttpServletRequest request) {
    	String mensajeRegreso="";
    	if(obtainUsername(request) != null && StringUtils.isNotEmpty(obtainUsername(request))){
			Usuario usuario = usuarioRepository.findByUsername(obtainUsername(request));
			ParametroGeneral parametroGeneral = parametroGeneralRepository.findByClave("intentosPermitidosLoggin");
			Integer intentosPermitidosLoggin = parametroGeneral ==null?5 : Integer.valueOf(parametroGeneral.getValor());
			parametroGeneral = parametroGeneralRepository.findByClave("minutosCuentaBloqueada");
			Integer minutosCuentaBloqueada = parametroGeneral ==null?30 : Integer.valueOf(parametroGeneral.getValor());
			Date tiempoAtras = TimeUtils.calculaTiempoatras(minutosCuentaBloqueada);
			if(usuario != null){
				Integer numeroDeIntentos = logginAttemptsRepository.countLogginAttemptsByUsuarioAndTiempoBetween(usuario,tiempoAtras,new Date()).intValue();
				if(superaNumeroIntentos(numeroDeIntentos,intentosPermitidosLoggin)){
					mensajeRegreso = "Cuenta bloquedada temporalmente por "+minutosCuentaBloqueada+" minutos.";
				}
				else{
					numeroDeIntentos++;
					mensajeRegreso = "Intento fallido "+numeroDeIntentos+" de "+intentosPermitidosLoggin+" intentos permitidos.";
					if(superaNumeroIntentos(numeroDeIntentos,intentosPermitidosLoggin)){
						mensajeRegreso += "\nCuenta bloquedada temporalmente por "+minutosCuentaBloqueada+" minutos.";
					}
					guardaNuevoIntentoFallido(usuario);
				}
			}else
				mensajeRegreso ="Credenciales invalidas";
		}else{
			mensajeRegreso ="Campos vacios!";
		}
		return mensajeRegreso;
	}
	private void guardaNuevoIntentoFallido(Usuario usuario) {
		logginAttemptsRepository.saveAndFlush(new LogginAttempts(usuario, new Date()));
		
	}
	private boolean superaNumeroIntentos(Integer numeroDeIntentos,Integer intentosPermitidosLoggin) {
		return numeroDeIntentos>=intentosPermitidosLoggin;
	}
	protected String obtainPassword(HttpServletRequest request) {
		return request.getParameter(passwordParameter);
	}
	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(usernameParameter);
	}
}
