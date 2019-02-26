package com.laliento.commontrunk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Entity
@Table(name="PARAMETRO_GENERAL")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.ToString
public class ParametroGeneral implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CLAVE",length=100,nullable=false)
	private String clave;
	@Column(name="VALOR",length=500,nullable=false)
	private String valor;
	@Column(name="DESCRIPCION",length=100,nullable=true)
	private String descripcion;
	
	public ParametroGeneral(String clave, String valor) {
		super();
		this.clave = clave;
		this.valor = valor;
	}
}
