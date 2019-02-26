package com.laliento.commontrunk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Entity
@Table(name="ENT_USUARIO")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.ToString
public class Usuario {
	@Id
	@Column(name="ID_USUARIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	@Column(name="USERNAME")
	private String username;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="APELLIDO_PATERNO")
	private String apellidoPaterno;
	@Column(name="APELLIDO_MATERNO")
	private String apellidoMaterno;
	@Column(name="CORREO")
	private String correo;
	@Column(name="PASSWORD",columnDefinition="char(128)")
	private String password;
	@Column(name="SALT",columnDefinition="char(128)")
	private String salt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ESTADO_USUARIO", nullable = false)
	private EstadoUsuario estadoUsuario;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERFIL", nullable = false)
	private Perfil perfil;
	@Column(name="ENABLE",columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enable;
	public Usuario(Integer idUsuario) {
		super();
		this.idUsuario = idUsuario;
	}
}