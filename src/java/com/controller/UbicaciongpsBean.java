/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Producto;
import com.entity.Tienda;
import com.entity.Ubicaciongps;
import com.services.TiendaServices;
import com.services.UbicaciongpsServices;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JOptionPane;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author DAC-PC
 */
@ManagedBean
@SessionScoped
public class UbicaciongpsBean {
         
    private MapModel advancedModel;
    private List<MapModel> listamapmodel = new ArrayList<>();
    private ArrayList<Ubicaciongps> listaubicacion = new ArrayList<>();
    private Marker marker;
    
    private MapModel product;
    private ArrayList<Ubicaciongps> listaubicacionproducto = new ArrayList<>();
    private Marker marker1;
    private String buscarproducto =null;
    private boolean mostraragotado = false;
        private ArrayList<Producto> listaprod = new ArrayList<>();
        private ArrayList<String> listaproductonombre = new ArrayList<>();
    public UbicaciongpsBean() {
     listarmap();
    }
    public void listarmap(){
        boolean estado = true;
        UbicaciongpsServices ubser = new UbicaciongpsServices();
        setListaubicacion((ArrayList<Ubicaciongps>) ubser.consultarTodo(Ubicaciongps.class));
        String logo = "http://icons.iconarchive.com/icons/custom-icon-design/flatastic-11/32/Shop-icon.png";
        advancedModel = new DefaultMapModel();
        for(int i = 0; i < getListaubicacion().size(); i++){
            LatLng coordi = new LatLng(Double.parseDouble(getListaubicacion().get(i).getLatitud()),Double.parseDouble(getListaubicacion().get(i).getLonguitud()));
           
            if(getListaubicacion().get(i).getTienda().isEstado()==true){
                advancedModel.addOverlay(new Marker(coordi, "Tienda " + getListaubicacion().get(i).getTienda().getNombretienda(),"http://gasmilenio.com/f/ICONO_TIENDACONVENIENCIA_s1.jpg", "http://icons.iconarchive.com/icons/fatcow/farm-fresh/32/shop-open-icon.png"));
            
             }
            if(getListaubicacion().get(i).getTienda().isEstado()==false){
                advancedModel.addOverlay(new Marker(coordi, "Tienda " + getListaubicacion().get(i).getTienda().getNombretienda(), "http://gasmilenio.com/f/ICONO_TIENDACONVENIENCIA_s1.jpg", "http://icons.iconarchive.com/icons/fatcow/farm-fresh/32/shop-closed-icon.png" ));
            }
           
            
        }
         
      
    
    }
    public void buscar(){
        TiendaServices ts = new TiendaServices();  
        setListaprod((ArrayList<Producto>) ts.listaproductobuscar(getBuscarproducto()));
        
        for(int i =0; i <getListaprod().size(); i++){
         listaproductonombre.add(getListaprod().get(i).getNombre());
        }
    }

   public void listarproductoenmapa(){
      
        TiendaServices ts = new TiendaServices();
         UbicaciongpsServices ubser = new UbicaciongpsServices();
     
        setListaprod((ArrayList<Producto>) ts.listaproductobuscar(getBuscarproducto()));
        ArrayList<Tienda> listatienda = new ArrayList<>();
        setListaubicacionproducto((ArrayList<Ubicaciongps>) ubser.consultarTodo(Ubicaciongps.class));
        if(listaprod.isEmpty()){
            setMostraragotado(true);
        }else{
            setMostraragotado(false);
        }
      
         
         product = new DefaultMapModel();
        
        for(int i =0; i < getListaubicacionproducto().size(); i++){
             for(int j =0; j < listaprod.size(); j++){
                if(getListaubicacionproducto().get(i).getTienda().equals(listaprod.get(j).getTienda())){
                 
                LatLng coordi = new LatLng(Double.parseDouble(getListaubicacionproducto().get(i).getLatitud()),Double.parseDouble(getListaubicacionproducto().get(i).getLonguitud()));
                    if(getListaubicacionproducto().get(i).getTienda().isEstado() == true){
                      product.addOverlay(new Marker(coordi,"Tienda con produco ", "Pan de queso","http://icons.iconarchive.com/icons/fatcow/farm-fresh/32/shop-open-icon.png" ));
                    }else{
                      product.addOverlay(new Marker(coordi,"Tienda con produco pero esta cerrada", "Pan de queso","http://icons.iconarchive.com/icons/fatcow/farm-fresh/32/shop-closed-icon.png" ));
                    }
                  
                }
             }
             
             
        }
      
      /* TiendaServices ts = new TiendaServices();
       ArrayList<Producto> listanoticia = new ArrayList<>();
       listanoticia = (ArrayList<Producto>) ts.listaproductobuscar("a");
       for(int i =0; i < listanoticia.size();i++){
       
           System.err.println(listanoticia.get(i).getNombreproducto());
       }*/
        
    }

    public List<MapModel> getListamapmodel() {
        return listamapmodel;
    }

    public void setListamapmodel(List<MapModel> listamapmodel) {
        this.listamapmodel = listamapmodel;
    }

    public ArrayList<Ubicaciongps> getListaubicacion() {
        return listaubicacion;
    }

    public void setListaubicacion(ArrayList<Ubicaciongps> listaubicacion) {
        this.listaubicacion = listaubicacion;
    }
    public MapModel getAdvancedModel() {
        return advancedModel;
    }
      
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }
      
    public Marker getMarker() {
        return marker;
    }

    public MapModel getProduct() {
        return product;
    }

    public void setProduct(MapModel product) {
        this.product = product;
    }

    public Marker getMarker1() {
        return marker1;
    }

    public void setMarker1(Marker marker1) {
        this.marker1 = marker1;
    }

    public ArrayList<Ubicaciongps> getListaubicacionproducto() {
        return listaubicacionproducto;
    }

    public void setListaubicacionproducto(ArrayList<Ubicaciongps> listaubicacionproducto) {
        this.listaubicacionproducto = listaubicacionproducto;
    }

    public String getBuscarproducto() {
        return buscarproducto;
    }

    public void setBuscarproducto(String buscarproducto) {
        this.buscarproducto = buscarproducto;
    }

    public boolean isMostraragotado() {
        return mostraragotado;
    }

    public void setMostraragotado(boolean mostraragotado) {
        this.mostraragotado = mostraragotado;
    }

    public ArrayList<Producto> getListaprod() {
        return listaprod;
    }

    public void setListaprod(ArrayList<Producto> listaprod) {
        this.listaprod = listaprod;
    }

    public ArrayList<String> getListaproductonombre() {
        return listaproductonombre;
    }

    public void setListaproductonombre(ArrayList<String> listaproductonombre) {
        this.listaproductonombre = listaproductonombre;
    }
    
}
