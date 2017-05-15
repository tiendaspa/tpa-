/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author DAC-PC
 */
@Entity
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombrevendedor;
    private String apellidovendedor;
    private String usuariovendedor;
    private String contrasenavendedor;
    private String cedulavendedor;
    private String telvendedor;
    
    @ManyToOne
    private Tienda tienda;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendedor)) {
            return false;
        }
        Vendedor other = (Vendedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Vendedor[ id=" + id + " ]";
    }

    public String getNombrevendedor() {
        return nombrevendedor;
    }

    public void setNombrevendedor(String nombrevendedor) {
        this.nombrevendedor = nombrevendedor;
    }

    public String getApellidovendedor() {
        return apellidovendedor;
    }

    public void setApellidovendedor(String apellidovendedor) {
        this.apellidovendedor = apellidovendedor;
    }

    public String getUsuariovendedor() {
        return usuariovendedor;
    }

    public void setUsuariovendedor(String usuariovendedor) {
        this.usuariovendedor = usuariovendedor;
    }

    public String getContrasenavendedor() {
        return contrasenavendedor;
    }

    public void setContrasenavendedor(String contrasenavendedor) {
        this.contrasenavendedor = contrasenavendedor;
    }

    public String getCedulavendedor() {
        return cedulavendedor;
    }

    public void setCedulavendedor(String cedulavendedor) {
        this.cedulavendedor = cedulavendedor;
    }

    public String getTelvendedor() {
        return telvendedor;
    }

    public void setTelvendedor(String telvendedor) {
        this.telvendedor = telvendedor;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
    
}
