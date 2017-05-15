/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implDao;

import com.dao.IDao;
import com.entity.Cliente;
import com.entity.Noticia;
import com.entity.Producto;
import com.entity.Tienda;
import com.entity.Ubicaciongps;
import java.util.List;

/**
 *
 * @author DAC-PC
 */
public interface ITienda extends IDao<Tienda, Long>{
    Tienda iniciarsesion(Tienda t);
    Long Obtenerultima();
    List<Cliente> Clienteestrella();
    List<Noticia> listarnoticias();
    Ubicaciongps obtenerubicacion(Tienda ti);
    Tienda validar(Tienda c);
    List<Producto> listaproductobuscar(String par);
}
