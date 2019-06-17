package com.laliento.commontrunk.repository;

import java.util.List;
import java.util.Map;

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
	
	

//	List<OrderEnc> findByOrderState(OrderState orderState);
//	@Query("select rpm from RelPerfilMenu rpm inner join rpm.catMenu cm where cm.menuPadre = :catMenu and rpm.perfil= :perfil order by cm.orden asc ")
//	@Query("select eoc from OrderEnc eoc where orderState = :orderState ")
//	@Query("select eoc from OrderEnc eoc inner join eoc.usuarioAdmin eu where eu.idUsuario = :usuarioAdmin and orderState = :orderState   ")
	List<OrderEnc> findByOrderState(OrderState orderState);

	//@Query(value = "select sum(price*QUANTITY) FROM ENT_ORDER_DET where id_order_enc = ?1 ", nativeQuery = true)
	@Query(value = "select eoc from OrderEnc eoc where usuarioDelivery = :usuario and orderState= :orderState ")
	List<OrderEnc> findByUsuarioDelivery(Usuario usuario, OrderState orderState);

	@Query(value = "select ID_ORDER_ENC,ID_ORDER_STATE FROM ENT_ORDER_ENC where ID_CUSTOMER_USER = ?1 and ID_ORDER_STATE in ( ?2 ) ", nativeQuery = true)
	List<Object> findOrdersByUser(Integer idUsuario, List<Integer> lstStatus);


}
