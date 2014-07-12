package com.degloba.boundedContext.webui.JSF.richfaces;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
import org.richfaces.component.UIPanelMenu;
import org.richfaces.component.UIPanelMenuGroup;
import org.richfaces.component.UIPanelMenuItem;

 /*
  * Classe que crea un panelMenu de Richfaces
  */
 
@ManagedBean
@RequestScoped
public class PMenu {

    private UIPanelMenu menu = null;
 
 
    public PMenu() {

        FacesContext fc = FacesContext.getCurrentInstance();

        Application application = FacesContext.getCurrentInstance().getApplication();

        menu = (UIPanelMenu) application.createComponent(fc, UIPanelMenu.COMPONENT_TYPE, "org.richfaces.PanelMenuRenderer");

        UIPanelMenuGroup group = (UIPanelMenuGroup) application.createComponent(fc, UIPanelMenuGroup.COMPONENT_TYPE,
            "org.richfaces.PanelMenuGroupRenderer");

        group.setLabel("group1");
        group.setId("gr1");

        UIPanelMenuItem item = (UIPanelMenuItem) application.createComponent(fc, UIPanelMenuItem.COMPONENT_TYPE,
            "org.richfaces.PanelMenuItemRenderer");

        item.setLabel("item1");
        item.setId("itm1");

        group.getChildren().add(item);

        item = (UIPanelMenuItem) application.createComponent(fc, UIPanelMenuItem.COMPONENT_TYPE,
            "org.richfaces.PanelMenuItemRenderer");

        item.setLabel("item2");
        item.setId("itm2");

        group.getChildren().add(item);
        menu.getChildren().add(group);

        group = (UIPanelMenuGroup) application.createComponent(fc, UIPanelMenuGroup.COMPONENT_TYPE,
            "org.richfaces.PanelMenuGroupRenderer");

        group.setLabel("group2");
        group.setId("gr2");

        item = (UIPanelMenuItem) application.createComponent(fc, UIPanelMenuItem.COMPONENT_TYPE,
            "org.richfaces.PanelMenuItemRenderer");

        item.setLabel("item3");
        item.setId("itm3");

        group.getChildren().add(item);

        item = (UIPanelMenuItem) application.createComponent(fc, UIPanelMenuItem.COMPONENT_TYPE,
            "org.richfaces.PanelMenuItemRenderer");

        item.setLabel("item4");
        item.setId("itm4");

        group.getChildren().add(item);
        menu.getChildren().add(group);

        item = (UIPanelMenuItem) application.createComponent(fc, UIPanelMenuItem.COMPONENT_TYPE,
            "org.richfaces.PanelMenuItemRenderer");

        item.setLabel("item5");
        item.setId("itm5");

        menu.getChildren().add(item);

    }

 
 
    public void setMenu(UIPanelMenu menu) {

        this.menu = menu;

    }

 
 
    public UIPanelMenu getMenu() {

        return menu;

    }

 
 
}

