/**
 * 
 */
package com.laliento.commontrunk.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.laliento.commontrunk.entity.OrderDet;
import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.service.OrderDetService;
import com.laliento.commontrunk.util.Constants;
import com.laliento.commontrunk.view.config.BackingBean;
import com.laliento.commontrunk.view.config.UserType;
import com.laliento.commontrunk.view.config.ViewMethodDefault;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@lombok.Getter
@Named
public class ConsultaOrderView extends BackingBean implements ViewMethodDefault{
	private static final long serialVersionUID = 1L;


	@Autowired
	OrderDetService orderDetService;

	@Getter @Setter
	private Integer orderId;
	
	@Getter @Setter
	private Integer idStatus;
	private Optional<OrderDet> lstOrderDet = null;
	private TreeMap<Integer,String> lstStatus;
	private List<OrderEnc> lstOrderEncs = null;
	private List<OrderDet> lstOrderDets = null;
	private BigDecimal total;
	
	@Getter @Setter
	private OrderEnc selectedOrder;

	@Override
	public String goPage() {
		loadElements();
		return createPage(UserType.ADMIN);
		
	}


	@Override
	public void loadElements() {
		lstStatus = new TreeMap<>();
		lstStatus.put(Constants.PENDING_ORDER.getInteger(), "Pendiente");
		lstStatus.put(Constants.ASSIGNED.getInteger(), "Asignado");
		lstStatus.put(Constants.ON_WAY.getInteger(), "En camino");
		lstStatus.put(Constants.DELIVERED.getInteger(), "Entregado");
		
	}
	
	public void findOrderById() {
		if(orderId!=null) {
			orderDetService.findOrderById(orderId);
			System.out.println("");
		}
	}
	
	public void findOrderByStatus() {
		if(idStatus!=null) {
			lstOrderEncs = orderDetService.findOrderByStatus(idStatus);
		}
		
	}
	
	public void viewDetail(){
		
		if(selectedOrder!=null) {
			lstOrderDets = orderDetService.findOrders(selectedOrder);
			total = orderDetService.getTotal(selectedOrder);
			System.out.println(total);
		}
		if(lstOrderDets==null)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sin registros en orden: "+selectedOrder.getIdEncOrderEnc()));
	}
		
		
}
