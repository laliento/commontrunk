package com.laliento.commontrunk.service;

import java.util.List;
import java.util.TreeMap;

import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.entity.Product;

/**
 * @author Miguel
 *
 */
public interface OrderDetServiceInterface {
	
	public Integer saveOrUpdate(OrderEnc orderEnc, TreeMap<Integer, Integer> order, List<Product> lstProductsAll);

}
