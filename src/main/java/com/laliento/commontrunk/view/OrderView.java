/**
 * 
 */
package com.laliento.commontrunk.view;

import javax.inject.Named;

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

	@Override
	public String goPage() {
		loadElements();
		return createPage(UserType.USER);
	}

	@Override
	public void loadElements() {
		// TODO Auto-generated method stub
		
	}
}
