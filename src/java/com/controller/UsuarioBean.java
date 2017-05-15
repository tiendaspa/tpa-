/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Cliente;
import com.entity.CodigoSesion;
import com.entity.Comentario;
import com.entity.Credito;
import com.entity.Detallepreventa;
import com.entity.Mora;
import com.entity.Notificacion;
import com.entity.Preventa;
import com.entity.Producto;
import com.entity.SesionesCliente;
import com.entity.Tienda;
import com.services.ClienteServices;
import com.services.CodigoServices;
import com.services.ComentarioServices;
import com.services.CreditoServices;
import com.services.DetallepreventaServices;
import com.services.MoraServices;
import com.services.NotificacionServices;
import com.services.PreventaServices;
import com.services.ProductoServices;
import com.services.SesionesClienteServices;
import com.services.UsuarioServices;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

import net.bootsfaces.utils.FacesMessages;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author DAC-PC
 */
@ManagedBean
@SessionScoped
public class UsuarioBean extends VariablesCSS{
    
    private CodigoSesion codigoSesion = new CodigoSesion();
    CodigoServices codserv = new CodigoServices();
    ClienteServices clienteser = new ClienteServices();
    UsuarioServices usuarioser = new UsuarioServices();
    ComentarioServices comentarioser = new ComentarioServices();
    ProductoServices productoser = new ProductoServices();
    PreventaServices preventaser = new PreventaServices();
    DetallepreventaServices detallepreventaserv = new  DetallepreventaServices();
    private Comentario comentario = new Comentario();
    private Cliente cliente = new Cliente();
    private Cliente verdatoscliente = new Cliente();
    private boolean panel = true;
    private boolean perfil = false;
    private boolean panelexito = false;
    private Cliente clientedatos;
    private UploadedFile img;
    private ArrayList<Producto> listaproducto = new ArrayList<>();
    private ArrayList<Preventa> preventa = new ArrayList<>();
    private ArrayList<Detallepreventa> detallepreventa = new ArrayList<>();
    private boolean mostrarbotones = false;
    private int notifycar =0;
    private double total =0;
    private double subtotal = 0;
    private int cont=0;
    TiendaBean tiendabean = new TiendaBean();
    ClienteBean clientebean = new ClienteBean();
    private int cantidad;
    private Detallepreventa detalleprev= new Detallepreventa();
    private Producto producto = new Producto();
    private boolean botonescarrito = false;
    private ArrayList<Producto> listarreciente = new ArrayList<>();
    private boolean añadiralcarro = true;
    SesionesClienteServices sesionesclienteserv = new SesionesClienteServices();
    private SesionesCliente sesionescliente = new SesionesCliente();
    private Credito creditocliente= new Credito();
    private double valorcredito=0;
    private String tipopreventa = null;
    CreditoServices creditoserv= new CreditoServices();
    NotificacionServices notificacionserv = new NotificacionServices();
    private ArrayList<Preventa> listadefactura = new ArrayList<>();
    private Part imgs;
    private Notificacion notificartienda = new Notificacion();
    
  
    public UsuarioBean() {
        
        setImgInicio("http://localhost:8084/TpaSolutions/faces/javax.faces.resource/public/fondoPrincipal.jpg");
        setDisplayInicio("flex");
        setDisplayTienda("none");
        setDisplayCliente("none");
        setMsjErrorCliente("none");
        setMsjSesion("none");
  
    }
    
    public void msjErrorC(){
        setMsjErrorCliente("none");
    }
    public void msjSesion(){
        setMsjSesion("none");
    }
     
    public void credito(){
        CreditoServices creditoserv= new CreditoServices();
        setCreditocliente(creditoserv.vercredito(obtenerCliente()));
        setValorcredito(getCreditocliente().getValor());
    }
    
      public void verReporte(long id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
 
        //Instancia hacia la clase reporteClientes        
        ReporteCliente rCliente = new ReporteCliente();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/Reportes/factura.jasper");
       
        rCliente.getReporte(ruta, obtenerCliente().getId(), id);        
        FacesContext.getCurrentInstance().responseComplete();               
    }
     
    public void añadiralcarrrito(Long id){
        if(obtenerCliente().getTienda().isEstado() == true){
        
        
        setProducto( productoser.verproducto(id));
        boolean existe = false;
           
        
            for(int i= 0; i < getDetallepreventa().size(); i++){
                if(getDetallepreventa().get(i).getProducto().equals(getProducto())){
                    FacesMessages.error("Usted ya tiene" + getProducto().getNombre() + " en su pedido");
                    existe = true;
                    setCantidad(0);
                }

            }    
            if(existe == false){ 
               
                Detallepreventa dp = new Detallepreventa();
               
                if(getProducto().getCantidad() >= getCantidad() && getCantidad() > 0){
                    dp.setProducto(getProducto());
                    dp.setCliente(obtenerCliente());
                    dp.setTienda(obtenerCliente().getTienda());
                    total += getProducto().getPrecio() * getCantidad();
                    dp.setCantidad(getCantidad());
                    subtotal = getProducto().getPrecio() * getCantidad();
                    dp.setSubtotal(subtotal);
                    setSubtotal(0);
                    detallepreventa.add(dp);
                    
              
               setNotifycar(detallepreventa.size());
                FacesMessages.info(getProducto().getNombre() +" se ha añadido al carrito");
            
                setBotonescarrito(true);
           
            }else{
                FacesMessages.warning("No existe la cantidad registrada en nuestro inventario, disculpe.");
            }
            setCantidad(0);
            
        }
        }else{
            FacesMessages.error(obtenerCliente().getTienda().getNombretienda() + " No se encuentra disponible en estos momentos.");
        }
    }
        public void listpreventas() throws IOException{
        long id = notificacionserv.Obtenerultimoclien(obtenerCliente());
        Notificacion n = notificacionserv.consultar(Notificacion.class,id);
 
        
        
        if(n != null){
          FacesMessages.info("Has recibido nuevos puntos de" + n.getTienda().getNombretienda());
          notificacionserv.eliminar(n);
        }
       
       
    
       
    }
 
    
    
    public void confirmarpedido(){
        MoraServices ms = new MoraServices();
        if(!(obtenerCliente().equals(ms.consultar(Mora.class, obtenerCliente().getId())))){
        Credito cre =null;
        Preventa c = new Preventa();
        Notificacion not = new Notificacion();
        
      
        
        try {
            if(getTipopreventa().equals("efectivo")){
            
                    
                    c.setCliente(obtenerCliente());
                    c.setTienda(obtenerCliente().getTienda());
                    c.setEstado((byte)0);
                    c.setTipo(getTipopreventa());
                    Date fecha = new Date();
                    c.setFecha(fecha);
                    c.setTotal(total);
                    preventaser.crear(c);
                   
                    generardetallepreventa();
                      not.setCliente(obtenerCliente());
                        not.setTienda(obtenerCliente().getTienda());
                            notificacionserv.crear(not);
                    detallepreventa.clear();
                    setNotifycar(detallepreventa.size());
                    setTotal(0);
                    FacesMessages.info("Su pedido se ha enviado " + obtenerCliente().getTienda().getNombretienda());
                    
                    setBotonescarrito(false);
                  
            }else if (getTipopreventa().equals("credito")) {
                   if(getValorcredito() >= getTotal()){
                    setCreditocliente(creditoserv.vercredito(obtenerCliente()));
                    double valorfinal = getValorcredito() - getTotal();
                    getCreditocliente().setValor(valorfinal);
                    creditoserv.modificar(getCreditocliente());
                    setValorcredito(valorfinal);
                    c.setCliente(obtenerCliente());
                    c.setTienda(obtenerCliente().getTienda());
                    c.setEstado((byte)0);
                    c.setTipo(getTipopreventa());
                    Date fecha = new Date();
                    c.setFecha(fecha);
                    c.setTotal(total);
                    preventaser.crear(c);

                    generardetallepreventa();
                    not.setCliente(obtenerCliente());
                    not.setTienda(obtenerCliente().getTienda());
                    notificacionserv.crear(not);
                    detallepreventa.clear();
                    setNotifycar(detallepreventa.size());
                    setTotal(0);
                    FacesMessages.info("Su pedido se ha enviado " + obtenerCliente().getTienda().getNombretienda());
                    setBotonescarrito(false);
                       
                      
                        
                   }else{
                     FacesMessages.error("Lo sentimos, usted no tiene fondos suficientes para realizar este pedido.");
                   }
            }
{
                
            }
        
                try{
                      Thread.sleep(2000);
                  }catch(InterruptedException e){

                  }
         
        } catch (Exception e) {
        }finally{
          
        }}else{
            FacesMessages.error("Posees una deuda en esta tienda.");
        }
  
      
    }
    public void generardetallepreventa(){
        long idpreventa = preventaser.ObtenerUltimo();
        ProductoServices ps = new ProductoServices();
                Detallepreventa detallepre = new Detallepreventa();
                Preventa c= preventaser.consultar(Preventa.class, idpreventa);
                Producto p;
                
            for(int i = 0; i < getDetallepreventa().size();i++){
                p = getDetallepreventa().get(i).getProducto();
                getDetallepreventa().get(i).setPreventa(c);
                detallepreventaserv.crear(getDetallepreventa().get(i));
                int cantidadproducto = p.getCantidad();
                int cantidadfinal = cantidadproducto - getDetallepreventa().get(i).getCantidad();
                p.setCantidad(cantidadfinal);
                ps.modificar(p);
                
            }
        
       
    }
    public void cancelarpedido(){
        detallepreventa.clear();
        setNotifycar(detallepreventa.size());
        setCont(0);
        setTotal(0);
        setBotonescarrito(false);
    }
   
  

    
  
    public void actualizardatos(){
        Cliente c = obtenerCliente();
        clienteser.modificar(c);
        setPanel(false);
        setPanelexito(true);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    
    
        public void TransferFile (String fileName , InputStream in){
            Rutaimg ruta = new Rutaimg();
        try{
            Cliente p = obtenerCliente();
            String[] a = fileName.split("\\.");
            String extension = a[1].toLowerCase();
                    String nombre = getCliente().getId()+"."+extension;
                    p.setImg(getCliente().getId()+"."+extension);
                    p.setNombre(getCliente().getNombre());
                    setCliente(p);
                    usuarioser.modificar(p);
            OutputStream out = new FileOutputStream ( new File(ruta.getRutaclientes()+nombre) );
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
    
    public void modificardatos(){
        
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
                }catch(IOException e){
                    
                }
            }
            
            
              try{
                    Thread.sleep(2000);
                }catch(InterruptedException e){}
            
        }
    }
    
    
    public void listarproductos(){
        Cliente c = obtenerCliente();
        setListaproducto((ArrayList<Producto>) productoser.listarproducto(c.getTienda()));
        setListadefactura((ArrayList<Preventa>) preventaser.listarpreventacliente(obtenerCliente()));
       
    }

    public void listarproductosrecientes(){
  
        listarreciente = (ArrayList<Producto>) detallepreventaserv.productoporcliente(obtenerCliente());
        
    }
    
    
        public void iniciarSesion(){
        
        CodigoSesion codigose;
        Cliente client = null;
        String redireccion= null;
      
   
        try {
          codigose = codserv.iniciarsesion(getCodigoSesion().getCod());
          
          if(codigose != null){
            client = codserv.comprobarsesion(codigose.getCliente().getId());
              if(client != null){
                   FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", client);
                   //redireccion = "/usuario?faces-redirect=true";
                   setClientedatos((Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cliente"));
                   codserv.eliminar(codigose);
                   FacesContext.getCurrentInstance().getExternalContext().redirect("/TpaSolutions/faces/usuario.xhtml");
                   getCliente().setUsuario("");
                   getCliente().setContrasena("");
              }
               
             
          }else{
            setImgInicio("http://localhost:8084/TpaSolutions/faces/javax.faces.resource/public/fondoCliente.jpg");
            setDisplayInicio("none"); 
            setDisplayCliente("flex");
            setMsjErrorCliente("block");
            
            getCliente().setUsuario("");
            getCliente().setContrasena("");
          }
         
        } catch (Exception e) {
            
        }
        //return redireccion;
    }
    
    public String iniciarSesionUser(){
        
       Cliente client;
       SesionesCliente validarsesion;

        String redireccion= null;
        try {
          client=  usuarioser.iniciarsesion(getCliente());
                     
          if(client != null){
              
              validarsesion = sesionesclienteserv.validarsesion(client);
              
              if(validarsesion==null){
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", client);
                setPanel(false);
                setPanelexito(true);
                setCliente((Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cliente"));
                listarproductos();
                listarproductosrecientes();
                setPerfil(true);
                getSesionescliente().setCliente(obtenerCliente());
                getSesionescliente().setTienda(obtenerCliente().getTienda());
                Date fecha = new Date();
                getSesionescliente().setFecha(fecha);
                sesionesclienteserv.crear(getSesionescliente());
                credito();
                
            
                 redireccion = "/usuario?faces-redirect=true";
                getCliente().setUsuario("");
                getCliente().setContrasena("");
              
              }else{
                setImgInicio("http://localhost:8084/TpaSolutions/faces/javax.faces.resource/public/fondoCliente.jpg");
                setDisplayInicio("none"); 
                setDisplayCliente("flex");
                getCliente().setUsuario("");
                getCliente().setContrasena("");
                setMsjSesion("block");
              }
                            
          }else{
            setImgInicio("http://localhost:8084/TpaSolutions/faces/javax.faces.resource/public/fondoCliente.jpg");
            setDisplayInicio("none"); 
            setDisplayCliente("flex");
            setMsjErrorCliente("block");
            
            getCliente().setUsuario("");
            getCliente().setContrasena("");
          }
         
        } catch (Exception e) {
            
        }
        return redireccion;
    }
    
    
    
    public String iniciarSesion_Original(){
        CodigoSesion codigose;
        Cliente client = null;
        String redireccion= null;
      
   
        try {
          codigose = codserv.iniciarsesion(getCodigoSesion().getCod());
          
          if(codigose != null){
            client = codserv.comprobarsesion(codigose.getCliente().getId());
              if(client != null){
                   FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", client);
                   redireccion = "/usuario?faces-redirect=true";
                   setClientedatos((Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cliente"));
                   codserv.eliminar(codigose);
              }
               
             
          }else{
              FacesMessages.error("Usuario no se ha encontrado!");
          }
         
        } catch (Exception e) {
            
        }
        return redireccion;
    }
    
    public String iniciarSesionUser_Original(){
       Cliente client;
       SesionesCliente validarsesion;

        String redireccion= null;
        try {
          client=  usuarioser.iniciarsesion(getCliente());
          
           
          if(client != null){
              
              validarsesion = sesionesclienteserv.validarsesion(client);
              
              if(validarsesion==null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", client);
                
                redireccion = "/usuario?faces-redirect=true";
                setPanel(false);
                setPanelexito(true);
                setCliente((Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cliente"));
                listarproductos();
                listarproductosrecientes();
                setPerfil(true);
                getSesionescliente().setCliente(obtenerCliente());
                getSesionescliente().setTienda(obtenerCliente().getTienda());
                Date fecha = new Date();
                getSesionescliente().setFecha(fecha);
                sesionesclienteserv.crear(getSesionescliente());
                  credito();
              
              }else{
                FacesMessages.error("Usted ya ha iniciado sesión el dia: " + validarsesion.getFecha()+  "Si no recuerda esto, vaya a la tienda donde se encuentra registrado.");
              }
              
              
          }else{
              FacesMessages.error("Usuario no se ha encontrado!");
          }
         
        } catch (Exception e) {
            
        }
        return redireccion;
    }
    public void enviarcomentario(){
        Cliente c = obtenerCliente();
        getComentario().setCliente(c);
        getComentario().setTienda(c.getTienda());
        
        comentarioser.crear(getComentario());
        clientebean.listarcomentarios();
        FacesMessages.info("Su comentario ha sido enviado a la tienda " + c.getTienda().getNombretienda());
        setComentario(new Comentario());
         
    }
    
    public void cerrarsesion(){
        SesionesCliente c = sesionesclienteserv.validarsesion(obtenerCliente());

        sesionesclienteserv.eliminar(c);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    
    }
    public Cliente obtenerCliente(){
        Cliente c;
        c= (Cliente)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cliente");
        return c;
    }
    public CodigoServices getCodserv() {
        return codserv;
    }

    public void setCodserv(CodigoServices codserv) {
        this.codserv = codserv;
    }

    public CodigoSesion getCodigoSesion() {
        return codigoSesion;
    }

    public void setCodigoSesion(CodigoSesion codigoSesion) {
        this.codigoSesion = codigoSesion;
    }

    public Cliente getClientedatos() {
        return clientedatos;
    }

    public void setClientedatos(Cliente clientedatos) {
        this.clientedatos = clientedatos;
    }

    public boolean isPanel() {
        return panel;
    }

    public void setPanel(boolean panel) {
        this.panel = panel;
    }

    public boolean isPanelexito() {
        return panelexito;
    }

    public void setPanelexito(boolean panelexito) {
        this.panelexito = panelexito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Cliente getVerdatoscliente() {
        return verdatoscliente;
    }

    public void setVerdatoscliente(Cliente verdatoscliente) {
        this.verdatoscliente = verdatoscliente;
    }

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }

    public ArrayList<Producto> getListaproducto() {
        return listaproducto;
    }

    public void setListaproducto(ArrayList<Producto> listaproducto) {
        this.listaproducto = listaproducto;
    }

    public ArrayList<Preventa> getPreventa() {
        return preventa;
    }

    public void setPreventa(ArrayList<Preventa> preventa) {
        this.preventa = preventa;
    }

    public ArrayList<Detallepreventa> getDetallepreventa() {
        return detallepreventa;
    }

    public void setDetallepreventa(ArrayList<Detallepreventa> detallepreventa) {
        this.detallepreventa = detallepreventa;
    }

    public boolean isMostrarbotones() {
        return mostrarbotones;
    }

    public void setMostrarbotones(boolean mostrarbotones) {
        this.mostrarbotones = mostrarbotones;
    }

    public int getNotifycar() {
        return notifycar;
    }

    public void setNotifycar(int notifycar) {
        this.notifycar = notifycar;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Detallepreventa getDetalleprev() {
        return detalleprev;
    }

    public void setDetalleprev(Detallepreventa detalleprev) {
        this.detalleprev = detalleprev;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public boolean isBotonescarrito() {
        return botonescarrito;
    }

    public void setBotonescarrito(boolean botonescarrito) {
        this.botonescarrito = botonescarrito;
    }


   /* public static void main(String[] args){
        DetallepreventaServices ds = new DetallepreventaServices();
        Tienda dd = new Tienda((long)1, "Cumbre Paisa", "la consolata", "Elcy Andrea Marin", "11536778899","cumbrepaisa" , "12345", "67527738");
        Cliente c = new Cliente((long)1, "Daniel Andrés", "Castillo Ardila", "danielcas", "12345", "1143403849", "daniel_andres.10@hotmail.com", "3005200495", "la consolata",dd, "1.jpg");
        ArrayList<Producto> prueba = (ArrayList<Producto>) ds.productoporcliente(c);
        for(int i =0; i < prueba.size();i++){
            System.out.println(prueba.get(i));
        }
    }*/

    public ArrayList<Producto> getListarreciente() {
        return listarreciente;
    }

    public void setListarreciente(ArrayList<Producto> listarreciente) {
        this.listarreciente = listarreciente;
    }

    public boolean isPerfil() {
        return perfil;
    }

    public void setPerfil(boolean perfil) {
        this.perfil = perfil;
    }

    public boolean isAñadiralcarro() {
        return añadiralcarro;
    }

    public void setAñadiralcarro(boolean añadiralcarro) {
        this.añadiralcarro = añadiralcarro;
    }

    public SesionesCliente getSesionescliente() {
        return sesionescliente;
    }

    public void setSesionescliente(SesionesCliente sesionescliente) {
        this.sesionescliente = sesionescliente;
    }

    public Credito getCreditocliente() {
        return creditocliente;
    }

    public void setCreditocliente(Credito creditocliente) {
        this.creditocliente = creditocliente;
    }

    public double getValorcredito() {
        return valorcredito;
    }

    public void setValorcredito(double valorcredito) {
        this.valorcredito = valorcredito;
    }

    public String getTipopreventa() {
        return tipopreventa;
    }

    public void setTipopreventa(String tipopreventa) {
        this.tipopreventa = tipopreventa;
    }

    public ArrayList<Preventa> getListadefactura() {
        return listadefactura;
    }

    public void setListadefactura(ArrayList<Preventa> listadefactura) {
        this.listadefactura = listadefactura;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Notificacion getNotificartienda() {
        return notificartienda;
    }

    public void setNotificartienda(Notificacion notificartienda) {
        this.notificartienda = notificartienda;
    }



}
