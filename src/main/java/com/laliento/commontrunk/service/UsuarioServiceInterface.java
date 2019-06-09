package com.laliento.commontrunk.service;

import java.util.List;

import com.laliento.commontrunk.entity.Usuario;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
public interface UsuarioServiceInterface {

	public List<Usuario> findAllByPerfil(Integer id);
}
