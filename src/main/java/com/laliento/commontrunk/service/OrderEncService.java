package com.laliento.commontrunk.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.repository.OrderEncRepository;

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
		return orderEncRepository.save(orderEnc);
	}

}
