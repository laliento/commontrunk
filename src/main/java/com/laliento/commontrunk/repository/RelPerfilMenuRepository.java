package com.laliento.commontrunk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.laliento.commontrunk.entity.CatMenu;
import com.laliento.commontrunk.entity.Perfil;
import com.laliento.commontrunk.entity.RelPerfilMenu;

@Repository
public interface RelPerfilMenuRepository extends JpaRepository<RelPerfilMenu, Integer>{

	public List<RelPerfilMenu> findByPerfil(Perfil perfil);
	@Query("select rpm from RelPerfilMenu rpm inner join rpm.catMenu cm where cm.menuPadre is null and rpm.perfil= :perfil order by cm.orden asc ")
	public List<RelPerfilMenu> findByPerilAndMenuIsNull(Perfil perfil);
	@Query("select rpm from RelPerfilMenu rpm inner join rpm.catMenu cm where cm.menuPadre = :catMenu and rpm.perfil= :perfil order by cm.orden asc ")
	public List<RelPerfilMenu> findByPerfilAndCatMenu(Perfil perfil, CatMenu catMenu);
}
