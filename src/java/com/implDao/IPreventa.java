/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implDao;

import com.dao.IDao;
import com.entity.Cliente;
import com.entity.Preventa;
import com.entity.Tienda;
import java.util.List;

/**
 *
 * @author DAC-PC
 */
public interface IPreventa extends IDao<Preventa, Long>{
    long ObtenerUltimo();
    List<Preventa> listarpreventa(Tienda c);
    List<Preventa> listarpreventacliente(Cliente cli);
}
