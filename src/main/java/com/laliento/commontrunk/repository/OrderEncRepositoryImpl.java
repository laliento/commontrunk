package com.laliento.commontrunk.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.entity.OrderState;

/**
 * @author Miguel
 *
 */
@Repository
public interface OrderEncRepositoryImpl extends JpaRepository<OrderEnc, Integer>{

//	List<OrderEnc> findByOrderState(OrderState orderState);
//	@Query("select rpm from RelPerfilMenu rpm inner join rpm.catMenu cm where cm.menuPadre = :catMenu and rpm.perfil= :perfil order by cm.orden asc ")
//	@Query("select eoc from OrderEnc eoc where orderState = :orderState ")
//	@Query("select eoc from OrderEnc eoc inner join eoc.usuarioAdmin eu where eu.idUsuario = :usuarioAdmin and orderState = :orderState   ")
//	List<OrderEnc> findByOrderState(OrderState orderState);

	List<OrderEnc> findByOrderState(OrderState orderState);

}
