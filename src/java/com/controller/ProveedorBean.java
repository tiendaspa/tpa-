/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Producto;
import com.entity.Proveedor;
import com.entity.Tienda;
import com.services.ProveedorServices;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author DAC-PC
 */
@ManagedBean
@SessionScoped
public class ProveedorBean {
    ProveedorServices proveedorServices = new ProveedorServices();
    private Proveedor proveedorregistrar = new Proveedor();
    private UploadedFile img;
    private ArrayList<Proveedor> listaproveedor = new ArrayList<>();
    private Proveedor proveedormodificar = new Proveedor();
    private boolean mostrartabla = true;
    private boolean mostrardetalles= false;
    public ProveedorBean() {
    }
    public void registrar(){
      
            String extValidate;
        if (getImg()!= null){
            String ext = getImg().getFileName();
            if (ext != null){
                extValidate = ext.substring(ext.indexOf(".")+1);
            } else{
                extValidate = "null";
            }
            
            if (extValidate.equals("jpg")){
                try{
                    
                    
                    TransferFile(getImg().getFileName(),getImg().getInputstream());
                    
                    proveedorServices.crear(getProveedorregistrar());
                    setProveedorregistrar(new Proveedor());
                    listar();
                }catch(IOException e){
                    
                }
            }
            
            
              try{
                    Thread.sleep(2000);
                }catch(InterruptedException e){}
            
        }
    }
     public Tienda Obtenertienda(){
        Tienda p= (Tienda) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tienda");
        return p;
    }
    
      public void TransferFile (String fileName , InputStream in){
                Rutaimg ruta = new Rutaimg();
        try{
            Tienda p = Obtenertienda();
            String[] a = fileName.split("\\.");
            String extension = a[1].toLowerCase();
                    String nombre = a[0]+"."+extension;
                    
                    getProveedorregistrar().setImg(p.getIdTienda()+"/"+a[0]+"."+extension);
                  
                    getProveedorregistrar().setTienda(Obtenertienda());
                    
                    //ps.crear(getPro());
                    //setPro(new Producto());
                    
            OutputStream out = new FileOutputStream ( new File(ruta.getRutaprovvedores()+p.getIdTienda()+"\\"+nombre));
            
            int reader = 0;
            byte[] bytes = new byte [(int)getImg().getSize()];
            while ((reader = in.read(bytes)) != -1){
                out.write(bytes,0,reader);
            }  
            in.close();
            out.flush();
            out.close();
        } catch(IOException e){
            System.out.println(e.getMessage());   
        }
    }
    public void listar(){
        setListaproveedor((ArrayList<Proveedor>) proveedorServices.listarproveedor(Obtenertienda()));
    }
    public void verproveedor(long id){
        setMostrartabla(false);
        setMostrardetalles(true);
        setProveedormodificar(proveedorServices.consultar(Proveedor.class, id));
    }
    
    public void modificar(){
        proveedorServices.modificar(getProveedormodificar());
        setMostrartabla(true);
        setMostrardetalles(false);
        
        
    }
    public void verproveedores(){
        setMostrartabla(true);
        setMostrardetalles(false);
        getProveedormodificar().setApellido("");
        getProveedormodificar().setCorreo("");
        getProveedormodificar().setNombre("");
        getProveedormodificar().setImg("");
        getProveedormodificar().setTelefono("");
    }

    public Proveedor getProveedorregistrar() {
        return proveedorregistrar;
    }

    public void setProveedorregistrar(Proveedor proveedorregistrar) {
        this.proveedorregistrar = proveedorregistrar;
    }

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }

    public ArrayList<Proveedor> getListaproveedor() {
        return listaproveedor;
    }

    public void setListaproveedor(ArrayList<Proveedor> listaproveedor) {
        this.listaproveedor = listaproveedor;
    }

    public Proveedor getProveedormodificar() {
        return proveedormodificar;
    }

    public void setProveedormodificar(Proveedor proveedormodificar) {
        this.proveedormodificar = proveedormodificar;
    }

    public boolean isMostrartabla() {
        return mostrartabla;
    }

    public void setMostrartabla(boolean mostrartabla) {
        this.mostrartabla = mostrartabla;
    }

    public boolean isMostrardetalles() {
        return mostrardetalles;
    }

    public void setMostrardetalles(boolean mostrardetalles) {
        this.mostrardetalles = mostrardetalles;
    }
    
}
