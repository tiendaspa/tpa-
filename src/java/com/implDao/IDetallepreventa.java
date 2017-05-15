/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implDao;


import com.dao.IDao;
import com.entity.Cliente;
import com.entity.Detallepreventa;
import com.entity.Preventa;
import com.entity.Producto;
import com.entity.Tienda;
import java.util.List;

/**
 *
 * @author DAC-PC
 */
public interface IDetallepreventa extends IDao<Detallepreventa, Long>{
    List<Detallepreventa> verdetalle(Preventa id);
    List<Producto> productoporcliente(Cliente c);
    List<Producto> vermascomprado(Tienda t);
    List<Integer> cantidad(Tienda ti);
    
}
