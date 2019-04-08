/**
 * 
 */
package com.laliento.commontrunk.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.laliento.commontrunk.entity.OrderEnc;
import com.laliento.commontrunk.entity.OrderState;
import com.laliento.commontrunk.entity.Product;
import com.laliento.commontrunk.entity.Usuario;
import com.laliento.commontrunk.service.OrderEncService;
import com.laliento.commontrunk.service.ProductService;
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
	
	private List<String> lstProducts;
	private TreeMap<String,Integer> order;
	@Getter @Setter
	private String item;
	@Getter @Setter
	private Integer cantidad;
	
	@Override
	public String goPage() {
		loadElements();
		order=null;
		return createPage(UserType.USER);
	}

	@Override
	public void loadElements() {
		List<Product> lstProductsAll = productService.findAll();
		if(lstProductsAll!=null) {
			lstProducts = new ArrayList<>();
			for (Product product : lstProductsAll) {
				lstProducts.add(product.getGenericDescription());
			}
			Collections.sort(lstProducts);
		}
		
	}
	
	public void addItem() {
		if(order==null) 
			order = new TreeMap<String, Integer>();
		
		if (order.containsKey(item)) {
			Integer suma = order.get(item);
			cantidad=cantidad+suma;
			suma=0;
		}
		if(item!=null && item.length()>0)
			order.put(item,cantidad);
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
			item=null;
			cantidad=null;
		}
	}
	
	public void saveOrder() {
		if(order!=null && order.size()>0) {
			Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			Usuario usuarioCustomer = new Usuario();
			usuarioCustomer.setIdUsuario(99);
			
			OrderEnc orderEnc = new OrderEnc();
			orderEnc.setUsuarioAdmin(usuario);
			orderEnc.setUsuarioDelivery(usuario);
			orderEnc.setUsuarioCustomer(usuario);
			orderEnc.setOrderState(new OrderState(1));
			orderEnc.setLastUpdate(new Date());
			orderEnc.setCreationDate(new Date());
			
			try {
				orderEncService.saveOrUpdate(orderEnc);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
}
