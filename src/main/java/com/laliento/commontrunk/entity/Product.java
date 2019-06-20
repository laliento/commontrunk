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
@Table(name="ENT_PRODUCT")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.ToString
public class Product {

	@Id
	@Column(name="ID_PRODUCT")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idProduct;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="ID_PRODUCT_STATE",nullable=false,referencedColumnName="ID_PRODUCT_STATE")
	private ProductState productState;
	@Column(name="PRICE",precision=15,scale=2,nullable=false)
	private BigDecimal price;
	@Column(name="GENERIC_DESCRIPTION",length=200,nullable=false)
	private String genericDescription;
	@Column(name="BRAND",length=200)
	private String brand;
	@Column(name="SUBBRAND",length=200)
	private String subBrand;
	@Column(name="MODEL_STYLE_TYPE",length=200)
	private String modelStyleType;
	
	public Product(Integer idProduct) {
		super();
		this.idProduct = idProduct;
	}
	
	
}
