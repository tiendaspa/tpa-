/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Cliente;
import com.entity.Debito;
import com.entity.Detalleventa;
import com.entity.Producto;
import com.entity.Tienda;
import com.entity.Venta;
import com.services.ClienteServices;
import com.services.DebitoServices;
import com.services.DetalleventaServices;
import com.services.ProductoServices;
import com.services.VentaServices;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import net.bootsfaces.utils.FacesMessages;
import org.primefaces.event.SelectEvent;


@ManagedBean
@SessionScoped
public class VentaBean {
    ProductoServices productoserv = new ProductoServices();
    VentaServices ventaserv = new VentaServices();
    ClienteServices clienteServices = new ClienteServices();
    DebitoServices debitoserv= new DebitoServices();
    
    DetalleventaServices detalleventaserv = new DetalleventaServices();
    private Producto productoventa = new Producto();
    private Cliente clienteventa = new Cliente();
    private Debito crerdebito = new Debito();
    private ArrayList<Detalleventa> listadetalleventa = new ArrayList<>();
    private int cantidad = 0;
    private ArrayList<Producto> listaproductos = new ArrayList<>();
    private ArrayList<Producto> listaproductosbebida = new ArrayList<>();
    private ArrayList<Producto> listaproductosgranos = new ArrayList<>();
    private ArrayList<Producto> listaproductospan = new ArrayList<>();
    private ArrayList<Producto> listaproductosaseo = new ArrayList<>();
    private ArrayList<Producto> listaproductoscarne = new ArrayList<>();
    private ArrayList<Cliente> listaclientes = new ArrayList<>();
    private double total = 0;
    private double subtotal = 0;
    private boolean mostrarbotonsiguiente = false;
    private boolean mostrarbotonventa = false;
    private boolean panelventa = false;
    private String tipo = null;
    private boolean tipoventa= false;
    private boolean tipoventacredito= false;
    private Date fechacreditopagar;
    private boolean mostrarcredito =true;
    /**
     * Creates a new instance of VentaBean
     */
    public VentaBean() {
        listarproducto();
        listarcliente();
    }
    public void eliminar(Long id) throws IOException{
     
        for(int i=0; i< getListadetalleventa().size(); i++){
            if(getListadetalleventa().get(i).getProducto().getId().equals(id)){
                total -= getListadetalleventa().get(i).getSubtotal();
                getListadetalleventa().remove(i);
            }
               if(getListadetalleventa().isEmpty()){
            setMostrarbotonsiguiente(false);
            setMostrarbotonventa(false);
            setTipoventa(false);
            setTotal(0);
            setPanelventa(false);
            setClienteventa(new Cliente());
            getListadetalleventa().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/venta.xhtml");
        }
        }
     
    
    }
    public void listarproducto(){
        setListaproductos((ArrayList<Producto>) productoserv.listarproducto(Obtenertienda()));
        
        for(int i=0; i < getListaproductos().size(); i++){
            if(getListaproductos().get(i).getCategoria().equals("Pan")){
              listaproductospan.add(getListaproductos().get(i));
            }
            if(getListaproductos().get(i).getCategoria().equals("Bebidas")){
              listaproductosbebida.add(getListaproductos().get(i));
            }
            if(getListaproductos().get(i).getCategoria().equals("Granos")){
              listaproductosgranos.add(getListaproductos().get(i));
            }
            if(getListaproductos().get(i).getCategoria().equals("Aseo")){
              listaproductosaseo.add(getListaproductos().get(i));
            }
        }
    
    }
    public void listarcliente(){
        setListaclientes((ArrayList<Cliente>) clienteServices.listarcliente(Obtenertienda()));
    }
    public void seleccionarcliente(Long id){
        setClienteventa(clienteServices.vercliente(id));
        setMostrarbotonsiguiente(false);
        setMostrarbotonventa(true);
        setTipoventa(true);
        setTipoventacredito(true);
    }
    public void omitircliente(){
        setClienteventa(null);
        setMostrarbotonsiguiente(false);
        setMostrarbotonventa(true);
        setTipoventacredito(false);
        setTipoventa(true);
        setMostrarcredito(false);
    }
    
    public void añadiralcarrito(Long id){
        setProductoventa(productoserv.verproducto(id));
       
              boolean existe = false;
           
        
            for(int i= 0; i < getListadetalleventa().size(); i++){
                if(getListadetalleventa().get(i).getProducto().equals(getProductoventa())){
                    FacesMessages.error("Usted ya tiene" + getProductoventa().getNombre() + " en su pedido");
                    existe = true;
                    setCantidad(0);
                }

            }
            if(existe == false){
                Detalleventa dv = new Detalleventa();
                dv.setProducto(getProductoventa());
                dv.setTienda(Obtenertienda());
                if(getProductoventa().getCantidad() > getCantidad() && getCantidad() > 0){
                    total += getProductoventa().getPrecio() * getCantidad();
                    dv.setCantidad(getCantidad());
                    subtotal = getProductoventa().getPrecio() * getCantidad();
                    dv.setSubtotal(subtotal);
                    
                    setSubtotal(0);
                    setMostrarbotonsiguiente(true);
                    setPanelventa(true);
              
              
                FacesMessages.info(getProductoventa().getNombre() +" se ha añadido al carrito");
            
                listadetalleventa.add(dv);
            }else{
                FacesMessages.warning("No existe la cantidad registrada en nuestro inventario, disculpe.");
            }
         setCantidad(0);
        
    }
    }
    public void confirmarpedido(){
        setMostrarcredito(true);
        Venta c = new Venta();
        try {
            if(getTipo().equals("efectivo")){
                    c.setCliente(getClienteventa());
                    c.setTienda(Obtenertienda());
                   
                    c.setTipo("Efectivo");
                    Date fecha = new Date();
                    c.setFecha(fecha);
                    c.setTotal(total);
                    ventaserv.crear(c);

                    generardetallepreventa();
                    getListadetalleventa().clear();
                    setMostrarbotonventa(false);
                    setPanelventa(false);
                    setTipoventa(false);
                    setTotal(0);
            FacesMessages.info("Venta realizada con exito");
            listarproducto();
             setClienteventa(new Cliente());
            }else if(getTipo().equals("credito")){
                c.setCliente(getClienteventa());
                    c.setTienda(Obtenertienda());
                    
                    c.setTipo("Credito");
                    Date fecha = new Date();
                    c.setFecha(fecha);
                    c.setTotal(total);
                    ventaserv.crear(c);

                    generardetallepreventa();
                
                crerdebito.setCliente(getClienteventa());
               crerdebito.setTienda(Obtenertienda());
               crerdebito.setEstado("Pendiente");
        
               crerdebito.setFechacredito(fecha);
               crerdebito.setFechapago(getFechacreditopagar());
               crerdebito.setTotal(total);
                long idpreventa = ventaserv.ObtenerUltimo();
                Venta p= ventaserv.consultar(Venta.class, idpreventa);
               crerdebito.setVenta(p);
               debitoserv.crear(crerdebito);
             FacesMessages.info("Credito realizado exitosamente!");
              getListadetalleventa().clear();
                    setMostrarbotonventa(false);
                    setPanelventa(false);
                    setTipoventa(false);
                    setTotal(0);
                    listarproducto();
            }
                 try{
                    Thread.sleep(2000);
                }catch(InterruptedException e){
                
                }
        } catch (Exception e) {
            FacesMessages.error("No se ha podido realizar venta.");
        }
    
    }
    public String stylecred(){
         String estilo;
        if(getClienteventa() == null){
           estilo= "opacity: 0";
        }else{
           estilo= "opacity: 10";
        }
    return estilo;
    }
      
    public void generardetallepreventa(){
        long idpreventa = ventaserv.ObtenerUltimo();
       
                Detalleventa detallepre = new Detalleventa();
               Venta c= ventaserv.consultar(Venta.class, idpreventa);
                Producto p;
                
            for(int i = 0; i < getListadetalleventa().size();i++){
                p = getListadetalleventa().get(i).getProducto();
                getListadetalleventa().get(i).setVenta(c);
                getListadetalleventa().get(i).setCliente(getClienteventa());
                detalleventaserv.crear(getListadetalleventa().get(i));
                int cantidadproducto = p.getCantidad();
                int cantidadfinal = cantidadproducto - getListadetalleventa().get(i).getCantidad();
                p.setCantidad(cantidadfinal);
                productoserv.modificar(p);
                
            }
        
       
    }
    
    
    
     public Tienda Obtenertienda(){
        Tienda p= (Tienda) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tienda");
        return p;
    }

    public Producto getProductoventa() {
        return productoventa;
    }

    public void setProductoventa(Producto productoventa) {
        this.productoventa = productoventa;
    }

    public ArrayList<Detalleventa> getListadetalleventa() {
        return listadetalleventa;
    }

    public void setListadetalleventa(ArrayList<Detalleventa> listadetalleventa) {
        this.listadetalleventa = listadetalleventa;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<Producto> getListaproductos() {
        return listaproductos;
    }

    public void setListaproductos(ArrayList<Producto> listaproductos) {
        this.listaproductos = listaproductos;
    }

    public ArrayList<Producto> getListaproductosbebida() {
        return listaproductosbebida;
    }

    public void setListaproductosbebida(ArrayList<Producto> listaproductosbebida) {
        this.listaproductosbebida = listaproductosbebida;
    }

    public ArrayList<Producto> getListaproductosgranos() {
        return listaproductosgranos;
    }

    public void setListaproductosgranos(ArrayList<Producto> listaproductosgranos) {
        this.listaproductosgranos = listaproductosgranos;
    }

    public ArrayList<Producto> getListaproductospan() {
        return listaproductospan;
    }

    public void setListaproductospan(ArrayList<Producto> listaproductospan) {
        this.listaproductospan = listaproductospan;
    }

    public ArrayList<Producto> getListaproductosaseo() {
        return listaproductosaseo;
    }

    public void setListaproductosaseo(ArrayList<Producto> listaproductosaseo) {
        this.listaproductosaseo = listaproductosaseo;
    }

    public ArrayList<Producto> getListaproductoscarne() {
        return listaproductoscarne;
    }

    public void setListaproductoscarne(ArrayList<Producto> listaproductoscarne) {
        this.listaproductoscarne = listaproductoscarne;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Cliente getClienteventa() {
        return clienteventa;
    }

    public void setClienteventa(Cliente clienteventa) {
        this.clienteventa = clienteventa;
    }

    public ArrayList<Cliente> getListaclientes() {
        return listaclientes;
    }

    public void setListaclientes(ArrayList<Cliente> listaclientes) {
        this.listaclientes = listaclientes;
    }
        /*public static void main(String[] args){
            DetalleventaServices ds = new DetalleventaServices();
            ClienteServices cs = new ClienteServices();
        Tienda t = new Tienda((long)1, "Cumbre Paisa", "la consolata","Andrea Marin", "1143564590", "cumbrepaisa", "12345","6778838");
        List<Cliente> id= cs.listarcliente(t);
        for(int i=0; i < id.size(); i++){
             System.out.println(""+id.get(i).getNombre());
        
        }
       
    
    }*/

    public boolean isMostrarbotonsiguiente() {
        return mostrarbotonsiguiente;
    }

    public void setMostrarbotonsiguiente(boolean mostrarbotonsiguiente) {
        this.mostrarbotonsiguiente = mostrarbotonsiguiente;
    }

    public boolean isMostrarbotonventa() {
        return mostrarbotonventa;
    }

    public void setMostrarbotonventa(boolean mostrarbotonventa) {
        this.mostrarbotonventa = mostrarbotonventa;
    }

    public boolean isPanelventa() {
        return panelventa;
    }

    public void setPanelventa(boolean panelventa) {
        this.panelventa = panelventa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isTipoventa() {
        return tipoventa;
    }

    public void setTipoventa(boolean tipoventa) {
        this.tipoventa = tipoventa;
    }

    public Date getFechacreditopagar() {
        return fechacreditopagar;
    }

    public void setFechacreditopagar(Date fechacreditopagar) {
        this.fechacreditopagar = fechacreditopagar;
    }

    public Debito getCrerdebito() {
        return crerdebito;
    }

    public void setCrerdebito(Debito crerdebito) {
        this.crerdebito = crerdebito;
    }

    public boolean isTipoventacredito() {
        return tipoventacredito;
    }

    public void setTipoventacredito(boolean tipoventacredito) {
        this.tipoventacredito = tipoventacredito;
    }

    public boolean isMostrarcredito() {
        return mostrarcredito;
    }

    public void setMostrarcredito(boolean mostrarcredito) {
        this.mostrarcredito = mostrarcredito;
    }



}
