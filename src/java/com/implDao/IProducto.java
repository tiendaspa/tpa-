/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implDao;

import com.dao.IDao;
import com.entity.Producto;
import com.entity.Tienda;
import java.util.List;

/**
 *
 * @author DAC-PC
 */
public interface IProducto extends IDao<Producto, Long>{
    List<Producto> listarproducto(Tienda c);
    Producto verproducto(Long id);
    long ObtenerUltimo();


}
