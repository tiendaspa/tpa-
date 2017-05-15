/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

/**
 *
 * @author Juan Manuel
 */
public class VariablesCSS {
    
        // VARIABLES PARA CONTRALAR EL FRONT END - LOGIN //
    
    private static String displayInicio,displayTienda,displayCliente;
    private static String imgInicio,imgTienda,imgCliente;
    private static String msjErrorTienda,msjErrorCliente,msjSesion;

    public String getDisplayInicio() {
        return displayInicio;
    }

    public void setDisplayInicio(String displayInicio) {
        this.displayInicio = displayInicio;
    }

    public String getDisplayTienda() {
        return displayTienda;
    }

    public void setDisplayTienda(String displayTienda) {
        this.displayTienda = displayTienda;
    }

    public String getDisplayCliente() {
        return displayCliente;
    }

    public void setDisplayCliente(String displayCliente) {
        this.displayCliente = displayCliente;
    }

    public String getImgInicio() {
        return imgInicio;
    }

    public void setImgInicio(String imgInicio) {
        this.imgInicio = imgInicio;
    }

    public String getImgTienda() {
        return imgTienda;
    }

    public void setImgTienda(String imgTienda) {
        this.imgTienda = imgTienda;
    }

    public String getImgCliente() {
        return imgCliente;
    }

    public void setImgCliente(String imgCliente) {
        this.imgCliente = imgCliente;
    }

    public String getMsjErrorTienda() {
        return msjErrorTienda;
    }

    public void setMsjErrorTienda(String msjErrorTienda) {
        VariablesCSS.msjErrorTienda = msjErrorTienda;
    }

    public String getMsjErrorCliente() {
        return msjErrorCliente;
    }

    public void setMsjErrorCliente(String msjErrorCliente) {
        VariablesCSS.msjErrorCliente = msjErrorCliente;
    }

    public String getMsjSesion() {
        return msjSesion;
    }

    public void setMsjSesion(String msjSesion) {
        VariablesCSS.msjSesion = msjSesion;
    }

}
