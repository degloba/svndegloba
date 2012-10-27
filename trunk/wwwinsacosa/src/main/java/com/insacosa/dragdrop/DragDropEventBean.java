package com.insacosa.dragdrop;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.richfaces.event.DropEvent;
import org.richfaces.event.DropListener;

@ManagedBean (name = "dragDropEventBean")
@RequestScoped
public class DragDropEventBean<T> implements DropListener {

    @ManagedProperty(value = "#{dragDropBeanCaract}")
    private DragDropBean<T> dragDropBean;

    public void setDragDropBean(DragDropBean<T> dragDropBean) {
        this.dragDropBean = dragDropBean;
    }

    public void processDrop(DropEvent event) {
        dragDropBean.moveCaracteristica((T) event.getDragValue());
    }
}