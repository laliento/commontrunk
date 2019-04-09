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
@Table(name="CAT_ORDER_STATE")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.ToString
public class OrderState {

	@Id
	@Column(name="ID_ORDER_STATE")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idOrderState;
	@Column(name="DESCRIPTION",nullable=false,length=45)
	private String description;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idEncOrderEnc")
	private Set<OrderEnc> order;
	
	public OrderState(Integer idOrderState) {
		super();
		this.idOrderState = idOrderState;
	}
	
	
}
