package com.laliento.commontrunk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laliento.commontrunk.entity.OrderEnc;

/**
 * @author Miguel
 *
 */
@Repository
public interface OrderEncRepository extends JpaRepository<OrderEnc, Integer>{

}
