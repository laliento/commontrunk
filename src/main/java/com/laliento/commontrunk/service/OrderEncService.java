package com.laliento.commontrunk.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.entity.OrderState;
import com.laliento.commontrunk.entity.Usuario;
import com.laliento.commontrunk.repository.OrderEncRepository;
import com.laliento.commontrunk.util.Constants;

/**
 * @author Miguel
 *
 */
@Service
@Transactional
public class OrderEncService implements OrderEncServiceInterface {
	
	@Autowired
	OrderEncRepository orderEncRepository;

	@Override
	public OrderEnc saveOrUpdate(OrderEnc orderEnc) {
		orderEnc.setUsuarioAdmin(new Usuario(Constants.ADMIN_USER.getInteger()));
		orderEnc.setUsuarioDelivery(new Usuario(Constants.DELIVERY_USER.getInteger()));
		orderEnc.setOrderState(new OrderState(Constants.PENDING_ORDER.getInteger()));
		orderEnc.setLastUpdate(new Date());
		orderEnc.setCreationDate(new Date());
		return orderEncRepository.save(orderEnc);
	}

}
