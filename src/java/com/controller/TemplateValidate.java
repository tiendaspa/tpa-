/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Cliente;
import com.entity.Tienda;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author DAC-PC
 */
@ManagedBean
@SessionScoped
public class TemplateValidate {

    /**
     * Creates a new instance of TemplateValidate
     */
    public TemplateValidate() {
    }
    public void verificar(){
        try {
            Tienda p= (Tienda) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tienda");
      
            if(p == null){
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../faces/index.xhtml");
            }
          
        } catch (Exception e) {
            
        }
    
    }
    public void verificarcliente(){
        try {
        
            Cliente c = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cliente");
            if(c == null){
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../faces/index.xhtml");
            }
          
        } catch (Exception e) {
            
        }
    
    }
    
    
}
