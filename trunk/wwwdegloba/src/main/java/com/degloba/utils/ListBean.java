package com.degloba.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean (name = "listBean")
@RequestScoped
public class ListBean {
    private String listType = "ordered";

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }
}
