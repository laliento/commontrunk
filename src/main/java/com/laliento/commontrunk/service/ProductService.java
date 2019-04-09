package com.laliento.commontrunk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laliento.commontrunk.entity.Product;
import com.laliento.commontrunk.entity.Usuario;
import com.laliento.commontrunk.repository.ProductRepository;
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
	
	@Autowired
	public ProductRepository productRepository;
	
	@Override
	public List<Usuario> buscaUsuario(){
		return usuarioRepository.findAll();
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	
}
