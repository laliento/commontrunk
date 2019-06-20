package com.laliento.commontrunk.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laliento.commontrunk.entity.OrderDet;
import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.entity.OrderState;
import com.laliento.commontrunk.entity.Product;
import com.laliento.commontrunk.repository.OrderDetRepository;
import com.laliento.commontrunk.repository.OrderEncRepository;

/**
 * @author Miguel
 *
 */
@Service
@Transactional
public class OrderDetService implements OrderDetServiceInterface{
	
	@Autowired
	OrderDetRepository orderDetRepository;
	
	@Autowired
	OrderEncRepository orderEncRepository;
	
	
	@Override
	public Integer saveOrUpdate(OrderEnc orderEnc, TreeMap<Integer, Integer> order, List<Product> lstProductsAll) {
		Integer records = 0;
		Map<Integer, String> lstPrice = new HashMap<>();
		for (Product product : lstProductsAll) {
			lstPrice.put(product.getIdProduct(), product.getPrice().toString());
		}
		for (Entry<Integer, Integer> entry : order.entrySet()) {
			OrderDet orderDet = new OrderDet();
			orderDet.setOrderEnc(orderEnc);
			orderDet.setProduct(new Product(entry.getKey()));
			orderDet.setQuantity(entry.getValue());
			BigDecimal price = new BigDecimal(lstPrice.get(entry.getKey()));
			orderDet.setPrice(price);
			
			try {
				orderDetRepository.save(orderDet);
				records++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return records;
	}

	public List<OrderDet> findOrderById(Integer orderId) {
//		List<Integer> lst = new ArrayList<>();
//		lst.add(orderId);
//		Optional<OrderDet> some = orderDetRepository.findById(orderId);
//		List<OrderDet> lstOrderDet = orderDetRepository.findByOrderEnc(new OrderEnc(orderId));
		return null;
	}

	public List<OrderEnc> findOrderByStatus(Integer idStatus) {
		List<OrderEnc> lstOrdersEnc = orderEncRepository.findByOrderState(new OrderState(idStatus));
		return lstOrdersEnc;
		
	}

	public List<OrderDet> findOrders(OrderEnc orderEnc) {
		List<OrderDet> lstOrdersDet = orderDetRepository.findAllByOrderEnc(orderEnc);
		return lstOrdersDet;
	}

	public BigDecimal getTotal(OrderEnc orderEnc) {
		BigDecimal total = orderDetRepository.getTotal(orderEnc);
		return total;
	}	
	
	
	

}
