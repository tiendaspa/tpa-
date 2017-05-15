/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implDao;

import com.dao.IDao;
import com.entity.Detalleventa;
import com.entity.Venta;
import java.util.List;

/**
 *
 * @author DAC-PC
 */
public interface IDetalleventa extends IDao<Detalleventa, Long>{
    List<Detalleventa> verdetalleventa(Venta c);
}
