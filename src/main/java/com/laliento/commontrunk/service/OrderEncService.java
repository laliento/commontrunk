package com.laliento.commontrunk.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public OrderEnc updateStatus(OrderEnc orderEnc) {
		return orderEncRepository.save(orderEnc);
	}
	
	@Override
	public List<OrderEnc> findOrderByUsuarioDelivery(Usuario usuario, OrderState orderState) {
		return orderEncRepository.findByUsuarioDelivery(usuario,orderState);
	}
	
	@Override
	public List<OrderEnc> findOrderByUsuarioDelivery(Usuario usuario, List<Integer> lstStatus) {
		Integer idUsuario = usuario.getIdUsuario();
		List<Object[]> lst = orderEncRepository.findOrdersByUser(idUsuario,lstStatus);
		List<OrderEnc> lstReturn = null;
		if(lst!=null & lst.size()>0) {
			lstReturn = new ArrayList<>();
			for (int i=0; i<lst.size(); i++){
				String id = lst.get(i)[0].toString();
				String idStatus = lst.get(i)[1].toString();
				OrderEnc orderEnc = new OrderEnc();
				orderEnc.setIdEncOrderEnc(Integer.valueOf(id));
				orderEnc.setOrderState(new OrderState(Integer.valueOf(idStatus)));
			   lstReturn.add(orderEnc);
			}
		}
		return lstReturn;
	}

}
