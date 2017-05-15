/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;


import com.entity.Cliente;
import com.entity.CodigoSesion;
import com.entity.Comentario;
import com.entity.Mora;
import com.entity.Tienda;

import com.services.ClienteServices;
import com.services.CodigoServices;
import com.services.ComentarioServices;
import com.services.MoraServices;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.bootsfaces.utils.FacesMessages;
import org.apache.commons.lang.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author DAC-PC
 */
@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable{
    private Cliente cliente= new Cliente();
    private ArrayList<Cliente> listacliente=new ArrayList<>();
    private ArrayList<Comentario> listacomentario = new ArrayList<>();
    private Comentario comentario= new Comentario();
    ClienteServices clienteserv= new ClienteServices();
    CodigoServices codservicesv = new CodigoServices();
    ComentarioServices comentarioserv = new ComentarioServices();
    private CodigoSesion codsesion = new CodigoSesion();
    TiendaBean tb = new TiendaBean();
    private boolean tabla = true;
    private boolean row = false;
    private boolean text = false;
    private boolean textcodigo= false;
    private boolean btnop=true;
    private int numero =0;
    private boolean vermensaje = false;
    private boolean vercomentarios = true;
    private String enviarcomentario;
    private boolean mostrarcatalogo = true;
    private boolean mostrardetalle_catalogo = false;
    private Cliente Clienteregistrar = new Cliente();
    private boolean clienteregistrado =false;

    public TiendaBean getTb() {
        return tb;
    }

    public void setTb(TiendaBean tb) {
        this.tb = tb;
    }
    public ClienteBean() {
     listar();
     listarcomentarios();
  
    }
    
  
    
    public static void send(String to, String sub,String msg) 
        
     {
         FacesMessage ms;
         final String user="tiendaspa2015@hotmail.com";
         final String pass="Proyectodeaula12345";
        Properties props = new Properties();
        //gmail: smtp.gmail.com
        //hotmail
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.port", "587");	
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getDefaultInstance(props,new Authenticator() 
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(user, pass);
            }
        });

        try 
        {
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);
            
         FacesMessages.info("Mensaje enviado");
        } catch (MessagingException e) 
        {
            FacesMessages.error("No se ha podido enviar Mensaje");
            
            throw new RuntimeException(e);
        }
        
    }
    public void habilitarcodigo(){
        setText(true);
        setTextcodigo(true);
        setBtnop(false);
    }
    public String style(){
       String estilo;
        if(getListacomentario().size() > 0){
           estilo= "background-color: red";
        }else{
           estilo= "background-color: green";
        }
    return estilo;
    }
    public void leerIDcomentario(Long id){
        setComentario(comentarioserv.vercomentario(id));
        setVermensaje(true);
        setVercomentarios(false);
    }
    public void registrar(){
        if(getClienteregistrar().getNombre().trim().equals("") || getClienteregistrar().getApellido().trim().equals("")   
               || getClienteregistrar().getCedula().trim().equals("") || getClienteregistrar().getDireccion().trim().equals("") 
               || getClienteregistrar().getCorreo().trim().equals("") ){
         FacesMessages.error("Por favor rellene todos los campos");
            setClienteregistrar(new Cliente());
        }else{
        
        MoraServices moraserv = new MoraServices();
        Boolean moroso = false;
        ArrayList<Mora> listamorosos = (ArrayList<Mora>) moraserv.consultarTodo(Mora.class);
        for(int i =0; i < listamorosos.size(); i++){
            if(listamorosos.get(i).getCliente().getCedula().equals(getClienteregistrar().getCedula())){
                moroso = true;
                FacesMessages.fatal("El cliente que intenta registrar se encuentra en la lista de morosos!");
            }
        }
        if(moroso == false){
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        getClienteregistrar().setTienda(Obtenertienda());
        getClienteregistrar().setEstado(true);
        clienteserv.crear(getClienteregistrar());
        setClienteregistrado(true);
        FacesMessages.info("Registro exitoso!");
        listar();
        setClienteregistrar(new Cliente());
        }
        setClienteregistrar(new Cliente());
        }
    }
    public void ocultarpanel(){
        setClienteregistrado(false);
    }
    
    public void notificarPUSH() {

        String summary = "Nuevo Elemento";
        String detail = "Se agrego a la lista";
        String CHANNEL = "/notify";

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml(summary), StringEscapeUtils.escapeHtml(detail)));
    }
    public void respondercliente(){
        Tienda t = Obtenertienda();
        send(getComentario().getCliente().getCorreo(), t.getNombretienda(), getEnviarcomentario());
        setVermensaje(false);
        setVercomentarios(true);
        comentarioserv.eliminar(getComentario());
        setEnviarcomentario("");
        listarcomentarios();
    }
    public Tienda Obtenertienda(){
        Tienda p= (Tienda) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tienda");
        return p;
    }
    public void listar(){
     
        setListacliente((ArrayList<Cliente>) clienteserv.listarcliente(Obtenertienda()));
    }
    public void listarcomentarios(){
        setListacomentario((ArrayList<Comentario>) comentarioserv.listarcomentario(Obtenertienda()) );
        setNumero(listacomentario.size());
    }
    public void resetcoment(){
        setNumero(0);
    }
    public void leerID(Long id){
        
        
        
        setCliente(clienteserv.vercliente(id));
        FacesMessages.info(getCliente().getNombre());

    }
    public void refresh(){
          setListacomentario((ArrayList<Comentario>) comentarioserv.listarcomentario(Obtenertienda()) );
        setNumero(listacomentario.size());
    }
    public void limpiar(){
        setCliente(new Cliente());
   }
    public void ocultartabla(){
    
        setTabla(false);
        setRow(true);
    }
    public void mostrartabla(){
    
        setCliente(new Cliente());
        setTabla(true);
        setRow(false);
        setText(false);
        setTextcodigo(false);
        setBtnop(true);
    }
    public void modificar(){
      
        clienteserv.modificar(getCliente());
        setRow(false);
        setTabla(true);
        setListacliente((ArrayList<Cliente>) clienteserv.listarcliente(Obtenertienda()));
        setCliente(new Cliente());
    }
    public void eliminar(){
        getCliente().setEstado(false);
        clienteserv.modificar(getCliente());
        setRow(false);
        setTabla(true);
        setListacliente((ArrayList<Cliente>) clienteserv.listarcliente(Obtenertienda()));
         setCliente(new Cliente());
    
    }
    public void guardarCodigo(){
        getCodsesion().setCliente(getCliente());
        
        codservicesv.crear(getCodsesion());
        send(getCodsesion().getCliente().getCorreo(), "CODIGO REGISTRO", getCodsesion().getCod());
        setText(false);
        setTextcodigo(false);
        setBtnop(true);
    
    
    }
    public void eliminarcomentario(){
    
      comentarioserv.eliminar(getComentario());
      listarcomentarios();
    }
    
    

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the listacliente
     */
    public ArrayList<Cliente> getListacliente() {
        return listacliente;
    }

    /**
     * @param listacliente the listacliente to set
     */
    public void setListacliente(ArrayList<Cliente> listacliente) {
        this.listacliente = listacliente;
    }

    public boolean isTabla() {
        return tabla;
    }

    public void setTabla(boolean tabla) {
        this.tabla = tabla;
    }

    public boolean isRow() {
        return row;
    }

    public void setRow(boolean row) {
        this.row = row;
    }

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public boolean isTextcodigo() {
        return textcodigo;
    }

    public void setTextcodigo(boolean textcodigo) {
        this.textcodigo = textcodigo;
    }

    public boolean isBtnop() {
        return btnop;
    }

    public void setBtnop(boolean btnop) {
        this.btnop = btnop;
    }

    public CodigoSesion getCodsesion() {
        return codsesion;
    }

    public void setCodsesion(CodigoSesion codsesion) {
        this.codsesion = codsesion;
    }

    public ArrayList<Comentario> getListacomentario() {
        return listacomentario;
    }

    public void setListacomentario(ArrayList<Comentario> listacomentario) {
        this.listacomentario = listacomentario;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isVermensaje() {
        return vermensaje;
    }

    public void setVermensaje(boolean vermensaje) {
        this.vermensaje = vermensaje;
    }

    public boolean isVercomentarios() {
        return vercomentarios;
    }

    public void setVercomentarios(boolean vercomentarios) {
        this.vercomentarios = vercomentarios;
    }

    public String getEnviarcomentario() {
        return enviarcomentario;
    }

    public void setEnviarcomentario(String enviarcomentario) {
        this.enviarcomentario = enviarcomentario;
    }

    public Cliente getClienteregistrar() {
        return Clienteregistrar;
    }

    public void setClienteregistrar(Cliente Clienteregistrar) {
        this.Clienteregistrar = Clienteregistrar;
    }

    public boolean isClienteregistrado() {
        return clienteregistrado;
    }

    public void setClienteregistrado(boolean clienteregistrado) {
        this.clienteregistrado = clienteregistrado;
    }

 
  
   
    
}
