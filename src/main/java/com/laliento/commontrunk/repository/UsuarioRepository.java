package com.laliento.commontrunk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.laliento.commontrunk.entity.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	public Usuario findByUsername(String username);
	
	public List<Usuario> findByPerfilIdPerfil(Integer id);

	@Query(value = "select ID_USUARIO from ENT_USUARIO where id_perfil = ?1 LIMIT 1 ", nativeQuery = true)
	public Integer findFirstAdmin(Integer integer);
}
