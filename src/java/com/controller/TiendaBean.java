/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;


import com.entity.Cliente;
import com.entity.Credito;
import com.entity.Detallepreventa;
import com.entity.Ingresos;
import com.entity.Noticia;
import com.entity.Notificacion;
import com.entity.Preventa;
import com.entity.Producto;
import com.entity.Tienda;
import com.entity.Ubicaciongps;
import com.services.ClienteServices;
import com.services.CreditoServices;
import com.services.DetallepreventaServices;
import com.services.IngresosServices;
import com.services.NoticiaServices;
import com.services.NotificacionServices;
import com.services.PreventaServices;
import com.services.ProductoServices;
import com.services.TiendaServices;
import com.services.UbicaciongpsServices;
import com.services.UsuarioServices;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.swing.JOptionPane;
import maps.java.Geocoding;

import net.bootsfaces.utils.FacesMessages;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;
import static sun.management.Agent.error;

/**
 *
 * @author DAC-PC
 */
@ManagedBean
@SessionScoped
public class TiendaBean extends VariablesCSS{
    private Tienda tienda = new Tienda();
    private Cliente client = new Cliente();
    private ArrayList<Tienda> listatienda = new ArrayList<>();
    private ArrayList<Preventa> listapre = new ArrayList<>();
    TiendaServices tiendaserv = new TiendaServices();
    UsuarioServices usuarioserv = new UsuarioServices();
    PreventaServices preventaserv = new PreventaServices();
    DetallepreventaServices detalleserv = new DetallepreventaServices();
    ClienteServices clienteserv= new ClienteServices();
    IngresosServices ingresosserv = new IngresosServices();
    private String hello =  null;
    private boolean mostrarpedidos= true;
    private boolean mostrardetalle =false;
    private ArrayList<Detallepreventa> listardetallepreventa = new ArrayList<>();
    private double ingresos = 0;
    private Preventa pre;
    private ArrayList<Cliente> listaclientemascomprador = new ArrayList<>();
    private LineChartModel productos = new LineChartModel();  
    NotificacionServices notificacionserv = new NotificacionServices();
    private ArrayList<Producto> listaproductomasvendido = new ArrayList<>();
    private ArrayList<Integer> cantidadproductomasvendido = new ArrayList<>();
    ChartSeries Productomasvendido  = new ChartSeries("Productomasvendido");
    private int Rating = 0;
    private int Reiting = 0;
    private Credito clientecredito = new Credito();
    private Cliente pruebacliente = new Cliente();
    private String password =null;
    private int prueba = 0;
    Utilidades utl = new Utilidades();
    private boolean mostraerror = false;
    private ArrayList<Cliente> listaclienteestrella = new ArrayList<>();
    private Noticia noticiacliente = new Noticia();
    private ArrayList<Noticia> listadonoticias = new ArrayList<>();
    private Producto productopromocionar = new  Producto();
    private boolean mostrarpanelpromocion = false;
    private boolean detenerpoll;
    private Ubicaciongps ubicaciongps = new Ubicaciongps();
    private Cliente cli = new Cliente();
    private Tienda ts = new Tienda();
    private String latitud =null;
    private String longuitud =null;
    private Ubicaciongps ubicaciontienda = new Ubicaciongps();
    
    public boolean isMostrarpanelpromocion() {
        return mostrarpanelpromocion;
    }

    public void setMostrarpanelpromocion(boolean mostrarpanelpromocion) {
        this.mostrarpanelpromocion = mostrarpanelpromocion;
    }
    public TiendaBean() {
        
        setImgInicio("http://localhost:8084/TpaSolutions/faces/javax.faces.resource/public/fondoPrincipal.jpg");
        setDisplayInicio("flex");
        setDisplayTienda("none");
        setDisplayCliente("none");
        setMsjErrorTienda("none");
        setMsjSesion("none");
    
        clientemascomprador();
        setPruebacliente(obtenerCliente());
        listarnoticias();

    }
    
    public void msjErrorT(){
        setMsjErrorTienda("none");
    }
    
    public List<Tienda> listartienda(){
        ArrayList<Tienda> listatiendaaaa = (ArrayList<Tienda>) tiendaserv.consultarTodo(Tienda.class);
        return listatiendaaaa;
    }
    public void probando(){
       
        setTs(tiendaserv.consultar(Tienda.class, getTienda().getIdTienda()));
        setUbicaciongps(tiendaserv.obtenerubicacion(getTs()));
        setLatitud(getUbicaciongps().getLatitud());
        setLonguitud(getUbicaciongps().getLonguitud());
    
    }
    public void openstore(){
         if(Obtenertienda().isEstado() ==  false){
             Obtenertienda().setEstado(true);
             tiendaserv.modificar(Obtenertienda());
         }else{
            Obtenertienda().setEstado(false);
            tiendaserv.modificar(Obtenertienda());
         }
    }
    
    
    public static void mostrarprueba(){
        FacesMessages.info("actualizado");
    }
    public void promocionar(Long id){
        ProductoServices ps = new ProductoServices();
        setProductopromocionar(ps.verproducto(id));
        setMostrarpanelpromocion(true);
    }
    public void promocionarproducto(){
        NoticiaServices ns = new NoticiaServices();
        Date fecha = new Date();
        getNoticiacliente().setFecha(fecha);
        getNoticiacliente().setProducto(getProductopromocionar());
        getNoticiacliente().setTienda(Obtenertienda());
        getNoticiacliente().setTitulo("Nuevo producto! " + Obtenertienda().getNombretienda() + "!");
        ns.crear(getNoticiacliente());
        setNoticiacliente(new Noticia());
        listarnoticias();
        FacesMessages.info(getProductopromocionar().getNombreproducto() + " Ha sido publicado en nuestro portal de noticias!");
        setProductopromocionar(new Producto());
        setMostrarpanelpromocion(false);
    }
    public void listarnoticias(){
  
        setListadonoticias((ArrayList<Noticia>) tiendaserv.listarnoticias());
    }
    
    public void publicarcliente(Long id){
        NoticiaServices noticiaserv = new NoticiaServices();
        Cliente cliente = clienteserv.vercliente(id);
        Date fecha = new Date();
        getNoticiacliente().setFecha(fecha);
        getNoticiacliente().setCliente(cliente);
        getNoticiacliente().setTienda(Obtenertienda());
        getNoticiacliente().setTitulo("Lo más fiel de " + Obtenertienda().getNombretienda() + "!");
        noticiaserv.crear(getNoticiacliente());
        setNoticiacliente(new Noticia());
        FacesMessages.info(cliente.getNombre() + " Ha sido publicado en nuestro portal de noticias!");
        listarnoticias();
    }

    public void productomasvendido(){
              setListaproductomasvendido((ArrayList<Producto>) detalleserv.vermascomprado(Obtenertienda()));
              setCantidadproductomasvendido((ArrayList<Integer>) detalleserv.cantidad(Obtenertienda()));
           
		
		
		
		
              for(int i=0;i< getListaproductomasvendido().size(); i++){
              Productomasvendido.set(getListaproductomasvendido().get(i).getNombre(), getCantidadproductomasvendido().get(i));
              productos.addSeries(Productomasvendido);
              }
		
	
		
		
		
		
		
    }
    
    public void notificacion(double valor, String nombre){
      FacesMessages.info("Enhorabuena! " + " Le has asignado " + valor + "$" + " a " + nombre);
    }
    public void clientemascomprador(){
        try {
            setListaclientemascomprador((ArrayList<Cliente>) ingresosserv.vermascomprador(Obtenertienda()));
        } catch (Exception e) {
        }
        
    }
      
    public void verclientecredito(Long id){
        double credito_modificar =0;
        CreditoServices creditoserv = new CreditoServices();
        Cliente c = clienteserv.vercliente(id);
        Credito credito = creditoserv.vercredito(c);
        clientecredito.setCliente(c);
        clientecredito.setTienda(Obtenertienda());
        
        switch(getRating())
        {
            case 1:
                if(credito == null){
                    clientecredito.setValor(1000.0);
                    creditoserv.crear(getClientecredito());
                    notificacion(clientecredito.getValor(), clientecredito.getCliente().getNombre());
                    setRating(0);
                }else{
                    credito_modificar = credito.getValor() + 1000.0;
                    credito.setValor(credito_modificar);
                    creditoserv.modificar(credito);
                    FacesMessages.info("Enhorabuena!, saldo actualizado. " + credito.getCliente().getNombre() + " Ahora cuenta con " + credito_modificar +"$");
                    setRating(0);
                }
               
            break;
            case 2:
                if(credito == null){
                    
                    setRating(0);
                    clientecredito.setValor(3000.0);
                    creditoserv.crear(getClientecredito());
                    notificacion(clientecredito.getValor(), clientecredito.getCliente().getNombre());
                }else{
                    setRating(0);
                    credito_modificar = credito.getValor() + 3000.0;
                    credito.setValor(credito_modificar);
                    creditoserv.modificar(credito);
                    FacesMessages.info("Enhorabuena!, saldo actualizado. " + credito.getCliente().getNombre() + " Ahora cuenta con " + credito_modificar +"$");
                
                }
            break;
            case 3:
                 if(credito == null){
                     setRating(0);
                    clientecredito.setValor(5000.0);
                    creditoserv.crear(getClientecredito());
                    notificacion(clientecredito.getValor(), clientecredito.getCliente().getNombre());
                }else{
                     setRating(0);
                    credito_modificar = credito.getValor() + 5000.0;
                    credito.setValor(credito_modificar);
                    creditoserv.modificar(credito);
                    FacesMessages.info("Enhorabuena!, saldo actualizado. " + credito.getCliente().getNombre() + " Ahora cuenta con " + credito_modificar +"$");
                
                }
            break;
            case 4:
              if(credito == null){
                  setRating(0);
                    clientecredito.setValor(7000.0);
                    creditoserv.crear(getClientecredito());
                    notificacion(clientecredito.getValor(), clientecredito.getCliente().getNombre());
                }else{
                  setRating(0);
                    credito_modificar = credito.getValor() + 7000.0;
                    credito.setValor(credito_modificar);
                    creditoserv.modificar(credito);
                    FacesMessages.info("Enhorabuena!, saldo actualizado. " + credito.getCliente().getNombre() + " Ahora cuenta con " + credito_modificar +"$");
                }
            break;
            case 5:
               if(credito == null){
                   setRating(0);
                    clientecredito.setValor(9000.0);
                    creditoserv.crear(getClientecredito());
                    notificacion(clientecredito.getValor(), clientecredito.getCliente().getNombre());
                }else{
                   setRating(0);
                    credito_modificar = credito.getValor() + 9000.0;
                    credito.setValor(credito_modificar);
                    creditoserv.modificar(credito);
                    FacesMessages.info("Enhorabuena!, saldo actualizado. " + credito.getCliente().getNombre() + " Ahora cuenta con " + credito_modificar +"$");
                }
            break;
        
        }
        
        
       
        
    
    }
      public Cliente obtenerCliente(){
        Cliente c;
        c= (Cliente)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cliente");
        return c;
    }
   
    
    public void listpreventas() throws IOException{
        long id = notificacionserv.ObtenerUltimo(Obtenertienda());
        Notificacion n = notificacionserv.consultar(Notificacion.class,id);
 
        ArrayList<Preventa> prueba = getListapre();
        setListapre((ArrayList<Preventa>) preventaserv.listarpreventa(Obtenertienda()));
        
        if(n != null){
          FacesMessages.info("Tienes un nuevo pedido de " + n.getCliente().getNombre());
          notificacionserv.eliminar(n);
        }
       
       
    
       
    }
    
    public boolean controlpoolling(){
        boolean ctrl  = false;
        long id = notificacionserv.ObtenerUltimo(Obtenertienda());
        Notificacion n = notificacionserv.consultar(Notificacion.class,id);
        if(n == null){
          return true;
        }
        return true;
    }
    
  
    public void verpreventa(Long id, Tienda t, Cliente c, Date fecha, String tipo, double total){
        setPre(new Preventa(id, t, c, fecha,tipo, total));
        setListardetallepreventa((ArrayList<Detallepreventa>) detalleserv.verdetalle(pre));
        setMostrarpedidos(false);
        setMostrardetalle(true);
    }

    public void confirmarpreventa() throws IOException{
        Ingresos ingreso = new Ingresos();
        int total =0;
        ArrayList<Detallepreventa> detallepreventa_temporal = new ArrayList<>();
        detallepreventa_temporal = getListardetallepreventa();
        for(int i=0;i < detallepreventa_temporal.size();i++){
            total +=(detallepreventa_temporal.get(i).getProducto().getPrecio() * detallepreventa_temporal.get(i).getCantidad());
            
        }
        ingreso.setCliente(detallepreventa_temporal.get(0).getCliente());
        ingreso.setTienda(Obtenertienda());
        ingreso.setTotal(total);
        ingresosserv.crear(ingreso);
        getPre().setEstado((byte)1);
        preventaserv.modificar(getPre());
        obteneringresos();
        clientemascomprador();
         setListapre((ArrayList<Preventa>) preventaserv.listarpreventa(Obtenertienda()));
        setMostrarpedidos(true);
        setMostrardetalle(false);
        getListardetallepreventa().clear();
        FacesMessages.info("Venta se ha realizado con exito!");
       
    }
    public void ignorarpreventa() throws IOException{
        ProductoServices ps = new ProductoServices();
        DetallepreventaServices ds = new DetallepreventaServices();
        PreventaServices pser = new PreventaServices();
        CreditoServices credser = new CreditoServices();
        ArrayList<Detallepreventa> detallepreventa_temporal;
        detallepreventa_temporal = getListardetallepreventa();
        Preventa c = detallepreventa_temporal.get(0).getPreventa();
        if(c.getTipo().equals("credito")){
            Credito cred = credser.vercredito(c.getCliente());
            double valoractual = cred.getValor() + c.getTotal();
            cred.setValor(valoractual);
            credser.crear(cred);
            
        }
        for(int i=0; i < detallepreventa_temporal.size(); i++){
       
            Producto p = detallepreventa_temporal.get(i).getProducto();
            int cantidad = p.getCantidad();
            int cantidadfinal = cantidad + detallepreventa_temporal.get(i).getCantidad();
            p.setCantidad(cantidadfinal);
            ps.modificar(p);
            Detallepreventa dp;
            dp = detallepreventa_temporal.get(i);
            ds.eliminar(dp);
            
         
            
        }
        pser.eliminar(c);
       
        setMostrarpedidos(true);
        setMostrardetalle(false);
        getListardetallepreventa().clear();
          setListapre((ArrayList<Preventa>) preventaserv.listarpreventa(Obtenertienda()));
          FacesMessages.error("Lo sentimos, no se ha podido realizar la venta");
        
    }
 
    public Tienda Obtenertienda(){
        Tienda p= (Tienda) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tienda");
        return p;
    }
    public void obteneringresos(){
        try {
            setIngresos(ingresosserv.obteneringresos(Obtenertienda()));
           
        } catch (Exception e) {
            
        }
        
    
    
    }
    
    public void clienteestrella(){
        setListaclienteestrella((ArrayList<Cliente>) tiendaserv.Clienteestrella());
    }
  
    
    public void mostrarcatalogo(){
        setMostrarpedidos(true);
        setMostrardetalle(false);
        getListardetallepreventa().clear();
    }
    
     public String iniciarSesion_Original(){
        Tienda tiend;
     
        String redireccion= null;
        try {
          tiend=  tiendaserv.iniciarsesion(getTienda());
      
           
          if(tiend != null){
              FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tienda", tiend);
              redireccion = "/tpasolution?faces-redirect=true";
              Tienda p = (Tienda) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tienda");
              setHello(p.getNombretienda());
              setListapre((ArrayList<Preventa>) preventaserv.listarpreventa(Obtenertienda()));
              productomasvendido();
              clientemascomprador();
          }else{
          
             
            setMostraerror(true);
            getTienda().setUsuario("");
            getTienda().setContraseña("");
                 
             
         
          }
         
        } catch (Exception e) {
            
        }
        return redireccion;
    }
     
    
    public void iniciarSesion(){
        Tienda tiend;
     
        String redireccion= null;
        try {
          tiend=  tiendaserv.iniciarsesion(getTienda());

          if(tiend != null){
              
              FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tienda", tiend);
              Tienda p = (Tienda) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tienda");
              setHello(p.getNombretienda());
              setListapre((ArrayList<Preventa>) preventaserv.listarpreventa(Obtenertienda()));
              productomasvendido();
              clientemascomprador();
              FacesContext.getCurrentInstance().getExternalContext().redirect("/TpaSolutions/faces/tpasolution.xhtml");
              getTienda().setUsuario("");
              getTienda().setContraseña("");
              
          }else{
              
            setImgInicio("http://localhost:8084/TpaSolutions/faces/javax.faces.resource/public/fondoTienda.jpg");
            setDisplayInicio("none"); 
            setDisplayTienda("flex");
            setMsjErrorTienda("block");
            
            getTienda().setUsuario("");
            getTienda().setContraseña("");

          }
         
        } catch (Exception e) {
            
        }
        
    } 
     
     
    public void cerrarsesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    
    }
    
    public void registrar(){
        if(tiendaserv.validar(getTienda()) == null){
        UbicaciongpsServices ubser = new UbicaciongpsServices();
        String pass=  utl.Encriptar(getPassword());
        Tienda tiendaubicacion = null;
        Long id;
        try {
            
           
            getTienda().setContraseña(pass);
            getTienda().setEstado(false);
            tiendaserv.crear(getTienda());
            id = tiendaserv.Obtenerultima();
            tiendaubicacion = new Tienda(id, getTienda().getNombretienda(), getTienda().getDireccion(), getTienda().getPropietario(), getTienda().getCedulapropietario(), getTienda().getUsuario(), pass, getTienda().getTelefono());
           
            Geocoding ObjGeocod=new Geocoding();
            try {
            
            Point2D.Double resultadoCD=ObjGeocod.getCoordinates(getTienda().getDireccion());
            getUbicaciongps().setTienda(tiendaubicacion);
            getUbicaciongps().setLatitud(""+resultadoCD.x);
            getUbicaciongps().setLonguitud(""+resultadoCD.y);
             ubser.crear(getUbicaciongps());
  
          
            } catch (Exception e) {
                error("Codificación geográfica");
            }
           
            crearCarpeta();
            FacesMessages.info(getTienda().getNombretienda() + " se ha registrado!");
            setTienda(new Tienda());
        } catch (Exception e) {
        }}else{
          FacesMessages.error("Ya existe una tienda con este nombre");
            setTienda(new Tienda());
                  
        }
        
    }
    
    public void crearCarpeta(){
        String ruta = "D:\\tpa-master\\tpa-master\\web\\resources\\img\\img_tienda\\";
        String ruta2 = "D:\\tpa-master\\tpa-master\\web\\resources\\img\\img_proveedor\\";
        long id= tiendaserv.Obtenerultima();
        File dir = new File(ruta+id);
        File dir2 = new File(ruta2+id);
        dir.mkdir();
        dir2.mkdir();
    }
    public void listar(){
        setListatienda((ArrayList<Tienda>) tiendaserv.consultarTodo(Tienda.class));
    }
    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public ArrayList<Tienda> getListatienda() {
        return listatienda;
    }

    public void setListatienda(ArrayList<Tienda> listatienda) {
        this.listatienda = listatienda;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }


    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public ArrayList<Preventa> getListapre() {
        return listapre;
    }

    public void setListapre(ArrayList<Preventa> listapre) {
        this.listapre = listapre;
    }



    public boolean isMostrardetalle() {
        return mostrardetalle;
    }

    public void setMostrardetalle(boolean mostrardetalle) {
        this.mostrardetalle = mostrardetalle;
    }

    public boolean isMostrarpedidos() {
        return mostrarpedidos;
    }

    public void setMostrarpedidos(boolean mostrarpedidos) {
        this.mostrarpedidos = mostrarpedidos;
    }

    public ArrayList<Detallepreventa> getListardetallepreventa() {
        return listardetallepreventa;
    }

    public void setListardetallepreventa(ArrayList<Detallepreventa> listardetallepreventa) {
        this.listardetallepreventa = listardetallepreventa;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }
     
  public static void main(String[] args){
       TiendaServices ts = new TiendaServices();
       ArrayList<Producto> listanoticia = new ArrayList<>();
       listanoticia = (ArrayList<Producto>) ts.listaproductobuscar("a");
       for(int i =0; i < listanoticia.size();i++){
       
           System.err.println(listanoticia.get(i).getTienda());
       }
       
    
    }

    public Preventa getPre() {
        return pre;
    }

    public void setPre(Preventa pre) {
        this.pre = pre;
    }

    public ArrayList<Cliente> getListaclientemascomprador() {
        return listaclientemascomprador;
    }

    public void setListaclientemascomprador(ArrayList<Cliente> listaclientemascomprador) {
        this.listaclientemascomprador = listaclientemascomprador;
    }

    public LineChartModel getProductos() {
        return productos;
    }

    public void setProductos(LineChartModel productos) {
        this.productos = productos;
    }

    public ArrayList<Producto> getListaproductomasvendido() {
        return listaproductomasvendido;
    }

    public void setListaproductomasvendido(ArrayList<Producto> listaproductomasvendido) {
        this.listaproductomasvendido = listaproductomasvendido;
    }

    public ArrayList<Integer> getCantidadproductomasvendido() {
        return cantidadproductomasvendido;
    }

    public void setCantidadproductomasvendido(ArrayList<Integer> cantidadproductomasvendido) {
        this.cantidadproductomasvendido = cantidadproductomasvendido;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }

    public Credito getClientecredito() {
        return clientecredito;
    }

    public void setClientecredito(Credito clientecredito) {
        this.clientecredito = clientecredito;
    }

    public int getReiting() {
        return Reiting;
    }

    public void setReiting(int Reiting) {
        this.Reiting = Reiting;
    }

    public Cliente getPruebacliente() {
        return pruebacliente;
    }

    public void setPruebacliente(Cliente pruebacliente) {
        this.pruebacliente = pruebacliente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPrueba() {
        return prueba;
    }

    public void setPrueba(int prueba) {
        this.prueba = prueba;
    }

    public boolean isMostraerror() {
        return mostraerror;
    }

    public void setMostraerror(boolean mostraerror) {
        this.mostraerror = mostraerror;
    }

    public ArrayList<Cliente> getListaclienteestrella() {
        return listaclienteestrella;
    }

    public void setListaclienteestrella(ArrayList<Cliente> listaclienteestrella) {
        this.listaclienteestrella = listaclienteestrella;
    }

    public Noticia getNoticiacliente() {
        return noticiacliente;
    }

    public void setNoticiacliente(Noticia noticiacliente) {
        this.noticiacliente = noticiacliente;
    }

    public ArrayList<Noticia> getListadonoticias() {
        return listadonoticias;
    }

    public void setListadonoticias(ArrayList<Noticia> listadonoticias) {
        this.listadonoticias = listadonoticias;
    }

    public Producto getProductopromocionar() {
        return productopromocionar;
    }

    public void setProductopromocionar(Producto productopromocionar) {
        this.productopromocionar = productopromocionar;
    }

    public boolean isDetenerpoll() {
        return detenerpoll;
    }

    public void setDetenerpoll(boolean detenerpoll) {
        this.detenerpoll = detenerpoll;
    }

    public Ubicaciongps getUbicaciongps() {
        return ubicaciongps;
    }

    public void setUbicaciongps(Ubicaciongps ubicaciongps) {
        this.ubicaciongps = ubicaciongps;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public Tienda getTs() {
        return ts;
    }

    public void setTs(Tienda ts) {
        this.ts = ts;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLonguitud() {
        return longuitud;
    }

    public void setLonguitud(String longuitud) {
        this.longuitud = longuitud;
    }

   
 
}
