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

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.laliento.commontrunk.entity.OrderDet;
import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.entity.OrderState;
import com.laliento.commontrunk.entity.Product;
import com.laliento.commontrunk.entity.Usuario;
import com.laliento.commontrunk.service.OrderDetService;
import com.laliento.commontrunk.service.OrderEncService;
import com.laliento.commontrunk.service.UsuarioService;
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
	
	@Autowired
	OrderEncService OrderEncService;
	
	@Autowired
	UsuarioService usuarioService;

	@Getter @Setter
	private Integer orderId;
	
	@Getter @Setter
	private Integer idStatus;
	
	@Getter @Setter
	private OrderEnc selectedOrder;
	
	@Getter @Setter
	private Integer idStatusChange;

	@Getter @Setter
	private Integer idUsuarioRepartidor;
	
	private Optional<OrderDet> lstOrderDet = null;
	private TreeMap<Integer,String> lstStatus;
	private TreeMap<Integer,String> lstRepartidores;
	private List<OrderEnc> lstOrderEncs = null;
	private List<OrderDet> lstOrderDets = null;
	private BigDecimal total;
	
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
		
		List<Usuario>lst = usuarioService.findAllByPerfil(1);
		lstRepartidores = new TreeMap<>();
		for (Usuario usuario: lst) {
			lstRepartidores.put(usuario.getIdUsuario(),usuario.getNombre()+" "+usuario.getApellidoPaterno());
		}
	}
	
	public void findOrderById() {
		if(orderId!=null) {
			orderDetService.findOrderById(orderId);
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
		}
		if(lstOrderDets==null)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sin registros en orden: "+selectedOrder.getIdEncOrderEnc()));
	}
	
	public void onRowEdit(RowEditEvent event) {
		if(idStatusChange!=null || idUsuarioRepartidor!=null) {
			OrderEnc orderEnc = (OrderEnc) event.getObject();
			orderEnc.setOrderState(new OrderState(idStatusChange));
				if(idUsuarioRepartidor!=null)
					orderEnc.setUsuarioDelivery(new Usuario(idUsuarioRepartidor));
			selectedOrder = OrderEncService.updateStatus(orderEnc);
			lstOrderEncs.remove(orderEnc);
		}
	}
		
}
