/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implDao;

import com.dao.IDao;
import com.entity.Producto;
import com.entity.Proveedor;
import com.entity.Tienda;
import java.util.List;

/**
 *
 * @author DAC-PC
 */
public interface IProveedor extends IDao<Proveedor, Long>{
     List<Proveedor> listarproveedor(Tienda c);
     Proveedor verproveedor(Long id);
}
