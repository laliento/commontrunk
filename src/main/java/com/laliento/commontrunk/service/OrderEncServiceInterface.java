package com.laliento.commontrunk.service;

import java.util.List;

import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.entity.OrderState;
import com.laliento.commontrunk.entity.Usuario;

/**
 * @author Miguel
 *
 */
public interface OrderEncServiceInterface {
	
	public OrderEnc saveOrUpdate(OrderEnc orderEnc);
	
	public List<OrderEnc> findOrderByUsuarioDelivery(Usuario usuario, OrderState orderState);
	
	public List<OrderEnc> findOrderByUsuarioDelivery(Usuario usuario, List<Integer> lstStatus);

}
