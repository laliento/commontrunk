package com.laliento.commontrunk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laliento.commontrunk.entity.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	public Usuario findByUsername(String username);
	
	public List<Usuario> findByPerfilIdPerfil(Integer id);
}
