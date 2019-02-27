/**
 * 
 */
package com.laliento.commontrunk.view;

import javax.inject.Named;

import com.laliento.commontrunk.view.config.ViewMethodDefault;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@lombok.Getter
@Named
public class OrderView implements ViewMethodDefault{

	@Override
	public void initPage() {
		// TODO Auto-generated method stub
	}

	@Override
	public String irPagina() {
		return "order.xhtml?faces-redirect=true";
	}

	@Override
	public void loadElements() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanPage() {
		// TODO Auto-generated method stub
		
	}

	
}
