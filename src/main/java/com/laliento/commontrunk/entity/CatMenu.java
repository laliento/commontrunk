package com.laliento.commontrunk.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.component.html.HtmlCommandLink;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Entity
@Table(name="CAT_MENU")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.ToString
public class CatMenu implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_MENU")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMenu;
	@Column(name="DESCRIPCION",length = 50)
	private String descripcion;
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ID_MENU_PADRE",nullable=true)
	private CatMenu menuPadre;
	@Column(name="URL",length=100,nullable=true)
	private String url;
	@Column	(name="ORDEN")
	private Integer orden;
	@Column(name="ICONO",length=50)
	private String icono;
	@OneToMany(mappedBy="menuPadre")
	private Set<CatMenu> subMenus = new HashSet<CatMenu>();
	@Transient
	private HtmlCommandLink link;
	public CatMenu(String descripcion, CatMenu menuPadre, String url, Integer orden,
			String icono) {
		super();
		this.descripcion = descripcion;
		this.menuPadre = menuPadre;
		this.url = url;
		this.orden = orden;
		this.icono = icono;
	}

	public CatMenu(String descripcion, CatMenu menuPadre, String url, Integer orden,
			String icono, Set<CatMenu> subMenus) {
		super();
		this.descripcion = descripcion;
		this.menuPadre = menuPadre;
		this.url = url;
		this.orden = orden;
		this.icono = icono;
		this.subMenus = subMenus;
	}

	public CatMenu(Integer idMenu) {
		super();
		this.idMenu = idMenu;
	}

	public CatMenu(String descripcion, String url, Integer orden, String icono) {
		this.descripcion = descripcion;
		this.url = url;
		this.orden = orden;
		this.icono = icono;
	}
}