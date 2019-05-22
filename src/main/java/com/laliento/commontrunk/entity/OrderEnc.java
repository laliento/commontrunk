/**
 * 
 */
package com.laliento.commontrunk.entity;

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
@Table(name = "ENT_ORDER_ENC")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.ToString
public class OrderEnc {

	@Id
	@Column(name = "ID_ORDER_ENC")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEncOrderEnc;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="ID_ADMIN_USER",nullable=false,referencedColumnName="ID_USUARIO")
	private Usuario usuarioAdmin;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="ID_DELIVERY_USER",nullable=false,referencedColumnName="ID_USUARIO")
	private Usuario usuarioDelivery;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="ID_CUSTOMER_USER",nullable=false,referencedColumnName="ID_USUARIO")
	private Usuario usuarioCustomer;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_ORDER_STATE",nullable=false,referencedColumnName="ID_ORDER_STATE")
	private OrderState orderState;
	@Column(name="CREATION_DATE",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@Column(name="LAST_UPDATE",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;
	@Column(name="ADITIONAL_INFO",length=500)
	private String aditionalInfo;
	
	public OrderEnc(Integer idEncOrderEnc) {
		super();
		this.idEncOrderEnc = idEncOrderEnc;
	}

	public OrderEnc(OrderState orderState) {
		super();
		this.orderState = orderState;
	}
	
	
}
