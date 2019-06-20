package com.laliento.commontrunk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.entity.OrderState;
import com.laliento.commontrunk.entity.Usuario;

/**
 * @author Miguel
 *
 */
@Repository
public interface OrderEncRepository extends JpaRepository<OrderEnc, Integer>{
	
	List<OrderEnc> findByOrderState(OrderState orderState);

	@Query(value = "select eoc from OrderEnc eoc where usuarioDelivery = :usuario and orderState= :orderState ")
	List<OrderEnc> findByUsuarioDelivery(Usuario usuario, OrderState orderState);

	@Query(value = "select ID_ORDER_ENC,ID_ORDER_STATE FROM ENT_ORDER_ENC where ID_CUSTOMER_USER = ?1 and ID_ORDER_STATE in ( ?2 ) ", nativeQuery = true)
//	@Query("select eoc from OrderEnc eoc inner join eoc.usuarioAdmin eu where eu.idUsuario = :usuarioAdmin and orderState in ( ?2 )  ")
	List<Object[]> findOrdersByUser(Integer idUsuario, List<Integer> lstStatus);


}
