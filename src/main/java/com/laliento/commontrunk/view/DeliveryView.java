/**
 * 
 */
package com.laliento.commontrunk.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.laliento.commontrunk.entity.OrderDet;
import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.entity.OrderState;
import com.laliento.commontrunk.entity.Usuario;
import com.laliento.commontrunk.service.OrderDetService;
import com.laliento.commontrunk.service.OrderEncService;
import com.laliento.commontrunk.util.Constants;
import com.laliento.commontrunk.view.config.BackingBean;
import com.laliento.commontrunk.view.config.UserType;
import com.laliento.commontrunk.view.config.ViewMethodDefault;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Miguel
 *
 */
@lombok.Getter
@Named
public class DeliveryView extends BackingBean implements ViewMethodDefault{
	private static final long serialVersionUID = 4L;

	@Autowired
	OrderEncService orderEncService;
	
	@Autowired
	OrderDetService orderDetService;
	
	@Getter @Setter
	private OrderEnc selectedOrderDelivery;
	
	@Getter @Setter
	private Integer idStatusChange;
	
	private List<OrderEnc> lstOrderEncsDelivery = null;
	private List<OrderDet> lstOrderDetsDelivery = null;
	private TreeMap<Integer,String> lstStatusDelivery;
	private BigDecimal total;
	
	@Override
	public String goPage() {
		loadElements();
		return createPage(UserType.DELIVERY);
	}

	@Override
	public void loadElements() {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		OrderState orderState = new OrderState(Constants.ASSIGNED.getInteger());
		lstOrderEncsDelivery = orderEncService.findOrderByUsuarioDelivery(usuario, orderState);
		
		lstStatusDelivery = new TreeMap<>();
		lstStatusDelivery.put(Constants.ON_WAY.getInteger(), "En camino");
		lstStatusDelivery.put(Constants.DELIVERED.getInteger(), "Entregado");
	}
	
	public void viewDetailDelivery(){
		if(selectedOrderDelivery!=null) {
			lstOrderDetsDelivery = orderDetService.findOrders(selectedOrderDelivery);
			total = orderDetService.getTotal(selectedOrderDelivery);
		}
		if(lstOrderDetsDelivery==null)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sin registros en orden: "+selectedOrderDelivery.getIdEncOrderEnc()));
	}
	
	public void onRowEdit(RowEditEvent event) {
		if(idStatusChange!=null) {
			OrderEnc orderEnc = (OrderEnc) event.getObject();
			if(!orderEnc.getOrderState().getIdOrderState().equals(idStatusChange))
				lstOrderEncsDelivery.remove(orderEnc);
			orderEnc.setOrderState(new OrderState(idStatusChange));
			selectedOrderDelivery = orderEncService.updateStatus(orderEnc);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado!"));
		}
	}
}