package com.laliento.commontrunk.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Entity
@Table(name="CAT_PERFIL")
@lombok.Getter
@lombok.Setter
@lombok.ToString
public class Perfil {

	@Id
	@Column(name="ID_PERFIL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPerfil;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	private Set<Usuario> usuario = new HashSet<Usuario>(0);
	
	public Perfil() {}
	public Perfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	public Perfil(Integer idPerfil,String descripcion) {
		this.idPerfil = idPerfil;
		this.descripcion = descripcion;
	}
}
