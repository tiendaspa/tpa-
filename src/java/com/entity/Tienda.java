/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author DAC-PC
 */
@Entity
public class Tienda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idtienda;
     @Column(name = "nombretienda", length = 100, nullable = false)
    private String nombretienda;
      @Column(name = "direccion", length = 100, nullable = false)
     private String direccion;
       @Column(name = "propietario", length = 100, nullable = false)
     private String propietario;
     @Column(name = "cedulapropietario", length = 20,nullable = false)
    private String cedulapropietario;
     @Column(name = "usuario", length = 20,nullable = false)
    private String usuario;
     @Column(name = "contrasena", length = 254,nullable = false)
    private String contrasena;
     @Column(name = "telefono", length = 50,nullable = false)
    private String telefono;
    private boolean estado;

    public Tienda(Long idtienda, String nombretienda, String direccion, String propietario, String cedulapropietario, String usuario, String contrasena, String telefono) {
        this.idtienda = idtienda;
        this.nombretienda = nombretienda;
        this.direccion = direccion;
        this.propietario = propietario;
        this.cedulapropietario = cedulapropietario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.telefono = telefono;
    }
    
     public Tienda(){
     }

    public Tienda(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Long getIdTienda() {
        return idtienda;
    }

    public void setIdTienda(Long idtienda) {
        this.idtienda = idtienda;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.idtienda);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tienda other = (Tienda) obj;
        if (!Objects.equals(this.idtienda, other.idtienda)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Tienda{" + "idtienda=" + idtienda + '}';
    }

    

    public String getNombretienda() {
        return nombretienda;
    }

    public void setNombretienda(String nombretienda) {
        this.nombretienda = nombretienda;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getCedulapropietario() {
        return cedulapropietario;
    }

    public void setCedulapropietario(String cedulapropietario) {
        this.cedulapropietario = cedulapropietario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contrasena;
    }

    public void setContraseña(String contraseña) {
        this.contrasena = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

  
}
