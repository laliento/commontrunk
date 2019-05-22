/**
 * 
 */
package com.laliento.commontrunk.entity;

import java.math.BigDecimal;

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
@Table(name="ENT_ORDER_DET")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.ToString
public class OrderDet {

	@Id
	@Column(name="ID_ORDER_DET")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idOrderDet;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_ORDER_ENC", nullable=false,referencedColumnName="ID_ORDER_ENC")
	private OrderEnc orderEnc;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PRODUCT",nullable=false,referencedColumnName="ID_PRODUCT")
	private Product product;
	@Column(name="PRICE",precision=15,scale=2)
	private BigDecimal price;
	@Column(name="QUANTITY",nullable=false)
	private Integer quantity;
	
	public OrderDet(Integer idOrderDet) {
		super();
		this.idOrderDet = idOrderDet;
	}

	public OrderDet(OrderEnc orderEnc) {
		super();
		this.orderEnc = orderEnc;
	}
	
	
}
