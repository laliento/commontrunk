package com.laliento.commontrunk.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Entity
@Table (name="LOGGIN_ATTEMPTS")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.ToString
public class LogginAttempts implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_LOGG_ATT")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idLoggAtt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ID_USUARIO",nullable=false,referencedColumnName="ID_USUARIO")
	private Usuario usuario;
	@Column(name="TIEMPO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tiempo;
	public LogginAttempts(Usuario usuario, Date tiempo) {
		this.usuario = usuario;
		this.tiempo = tiempo;
	}
}
