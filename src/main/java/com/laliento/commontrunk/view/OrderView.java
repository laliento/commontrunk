/**
 * 
 */
package com.laliento.commontrunk.view;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.entity.OrderState;
import com.laliento.commontrunk.entity.Product;
import com.laliento.commontrunk.entity.Usuario;
import com.laliento.commontrunk.service.OrderDetService;
import com.laliento.commontrunk.service.OrderEncService;
import com.laliento.commontrunk.service.ProductService;
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
public class OrderView extends BackingBean implements ViewMethodDefault{
	private static final long serialVersionUID = 1L;

	@Autowired
	ProductService productService;
	
	@Autowired
	OrderEncService orderEncService;

	@Autowired
	OrderDetService orderDetService;
	
	private List<String> lstProducts;
	private TreeMap<Integer,Integer> order;
	private TreeMap<String,Integer> orderShow;
	private List<Product> lstProductsAll;
	private TreeMap<Integer,String> lstProductsprice;
	private List<OrderEnc> lstOrderEncsDelivery;
	private List<Integer> lstStatus;
	@Getter @Setter
	private Integer item;
	@Getter @Setter
	private Integer cantidad;
	
	@Override
	public String goPage() {
		loadElements();
		order=null;
		orderShow=null;
		return createPage(UserType.USER);
	}

	@Override
	public void loadElements() {
		lstProductsAll = productService.findAll();
		lstProductsprice = new TreeMap<>();
		for (Product product : lstProductsAll) {
			lstProductsprice.put(product.getIdProduct(), product.getGenericDescription()+"  $"+product.getPrice());
		}
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		lstStatus = new ArrayList<>();
		lstStatus.add(Constants.ON_WAY.getInteger());
		lstStatus.add(Constants.DELIVERED.getInteger());
		lstOrderEncsDelivery = orderEncService.findOrderByUsuarioDelivery(usuario, lstStatus);
		System.out.println("WWWw");
	}
	
	public void addItem() {
		if(order==null) { 
			order = new TreeMap<Integer, Integer>();
			orderShow = new TreeMap<String, Integer>();
		}
		if (order.containsKey(item)) {
			Integer suma = order.get(item);
			cantidad=cantidad+suma;
			suma=0;
		}
		if(item!=null && cantidad>0) {
			order.put(item,cantidad);
			orderShow.put(lstProductsprice.get(item), cantidad);
		}
		item=null;
		cantidad=null;
	}
	
	public void removeItem() {
		if(order!=null) {
			if (order.containsKey(item)) {
				Integer resta = order.get(item);
				cantidad=resta-cantidad;
				resta=0;
				if(cantidad<resta)
					cantidad=0;
			}
			order.put(item,cantidad);
			orderShow.put(lstProductsprice.get(item), cantidad);
			if(cantidad.equals(0)) {
				order.remove(item);
				orderShow.remove(lstProductsprice.get(item));
			}
			item=null;
			cantidad=null;
		}
	}
	
	public void saveOrder() {
		if(order!=null && order.size()>0) {
			Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			OrderEnc orderEnc = new OrderEnc();
			orderEnc.setUsuarioCustomer(usuario);
			Integer records = null;
			try {
				orderEnc = orderEncService.saveOrUpdate(orderEnc);
				if(orderEnc!=null && orderEnc.getIdEncOrderEnc()!=null) {
					records = orderDetService.saveOrUpdate(orderEnc, order, lstProductsAll);
					order = null;
					orderShow=null;
				}
				if(records!=null && records>0)
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se ha guardado tu orden con: ", records+" productos!"));
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!","Error al guardar tu orden!"));
				e.printStackTrace();
			}
		}
		Runtime.getRuntime().gc();
	}
	
}
