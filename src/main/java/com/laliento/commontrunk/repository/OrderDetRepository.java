package com.laliento.commontrunk.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.laliento.commontrunk.entity.OrderDet;
import com.laliento.commontrunk.entity.OrderEnc;

/**
 * @author Miguel
 *
 */
public interface OrderDetRepository extends JpaRepository<OrderDet, Integer>{
	
	public List<OrderDet> findByOrderEnc(OrderEnc orderEnc);

	public List<OrderDet> findAllByOrderEnc(OrderEnc orderEnc);
	
	@Query(value = "select sum(price*QUANTITY) FROM ENT_ORDER_DET where id_order_enc = ?1 ", nativeQuery = true)
	public BigDecimal getTotal(OrderEnc orderEnc);

}
