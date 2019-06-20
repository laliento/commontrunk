package com.laliento.commontrunk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.entity.OrderState;

/**
 * @author Miguel
 *
 */
@Repository
public interface OrderEncRepositoryImpl extends JpaRepository<OrderEnc, Integer>{

	List<OrderEnc> findByOrderState(OrderState orderState);

}
