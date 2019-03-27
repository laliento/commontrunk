package com.laliento.commontrunk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laliento.commontrunk.entity.Usuario;
import com.laliento.commontrunk.repository.UsuarioRepository;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Service
@Transactional
public class ProductService implements ProductServiceInterface{

	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@Override
	public List<Usuario> buscaUsuario(){
		
		return usuarioRepository.findAll();
	}
	
	
}
