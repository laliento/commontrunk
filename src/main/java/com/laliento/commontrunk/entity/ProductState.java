/**
 * 
 */
package com.laliento.commontrunk.entity;

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
@Table(name="CAT_PRODUCT_STATE")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.ToString
public class ProductState {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PRODUCT_STATE")
	private Integer IdProductState;
	@Column(name="DESCRIPTION",length=45,nullable=false)
	private String description;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="productState")
	private Set<Product> product;
	
}
