/**
 * 
 */
package com.laliento.commontrunk.view;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.laliento.commontrunk.entity.Usuario;
import com.laliento.commontrunk.service.ProductService;
import com.laliento.commontrunk.view.config.BackingBean;
import com.laliento.commontrunk.view.config.UserType;
import com.laliento.commontrunk.view.config.ViewMethodDefault;

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
	
	@Override
	public String goPage() {
		loadElements();
		return createPage(UserType.USER);
	}

	@Override
	public void loadElements() {
		List<Usuario> lst =  productService.buscaUsuario();
	}
}
