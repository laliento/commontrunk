package com.laliento.commontrunk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laliento.commontrunk.entity.Usuario;
import com.laliento.commontrunk.repository.UsuarioRepository;

/**
 * @author Miguel
 *
 */
@Service
@Transactional
public class UsuarioService implements UsuarioServiceInterface{
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public List<Usuario> findAllByPerfil(Integer id) {
		return usuarioRepository.findByPerfilIdPerfil(id);
	}	
	
}
