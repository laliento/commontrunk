package com.laliento.commontrunk.view.config;

import java.util.LinkedHashSet;
import java.util.List;

import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.laliento.commontrunk.entity.CatMenu;
import com.laliento.commontrunk.entity.RelPerfilMenu;
import com.laliento.commontrunk.entity.Usuario;
import com.laliento.commontrunk.repository.RelPerfilMenuRepository;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Named
@lombok.Getter
public class LoginView extends BackingBean implements ViewMethodDefault{
	private static final long serialVersionUID = 1L;
	@Autowired
	private RelPerfilMenuRepository relPerfilMenuRepository;
	private List<RelPerfilMenu> lstRelPerfilMenu;
	private Usuario usuario;
	@Override
	public String goPage() {
		String pagina =getPageByUser(); 
		if (pagina != null)
			loadElements();
		return pagina;
	}
	@Override
	public void loadElements() {
		lstRelPerfilMenu= relPerfilMenuRepository.findByPerilAndMenuIsNull(getSessionUser().getPerfil());
		for (RelPerfilMenu relPerfilMenu : lstRelPerfilMenu) {
			LinkedHashSet <CatMenu> subMenus = new LinkedHashSet <CatMenu>();
			for (RelPerfilMenu relPerfilMenuSub : relPerfilMenuRepository.findByPerfilAndCatMenu(getSessionUser().getPerfil(),relPerfilMenu.getCatMenu())) {
				HtmlCommandLink link = new HtmlCommandLink();
				link.setValue(relPerfilMenuSub.getCatMenu().getDescripcion());
				link.setTitle(relPerfilMenuSub.getCatMenu().getDescripcion());
				link.setStyleClass("item-text");
				link.setActionExpression(createActionExpression(relPerfilMenuSub.getCatMenu().getUrl()));
				relPerfilMenuSub.getCatMenu().setLink(link);
				subMenus.add(relPerfilMenuSub.getCatMenu());
			}
			relPerfilMenu.getCatMenu().setSubMenus(subMenus);
		}
	}
	private MethodExpression createActionExpression(String url) {
        Application app = FacesContext.getCurrentInstance().getApplication();
        MethodExpression action = app.getExpressionFactory().createMethodExpression(
                        FacesContext.getCurrentInstance().getELContext(),
                        "#{"+ url +"}", String.class, new Class[0]);
        return action;
	}
}
