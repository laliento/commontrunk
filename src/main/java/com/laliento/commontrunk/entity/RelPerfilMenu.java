package com.laliento.commontrunk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Entity
@Table(name="REL_PERFIL_MENU")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.ToString
public class RelPerfilMenu implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_REL_PERFIL_MENU")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRelPerfilMenu;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERFIL", nullable = false)
	private Perfil perfil;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MENU", nullable = false)
	private CatMenu  catMenu;
	public RelPerfilMenu(Integer idRelPerfilMenu) {
		this.idRelPerfilMenu = idRelPerfilMenu;
	}
}
