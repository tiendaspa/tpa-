/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implDao;

import com.dao.IDao;
import com.entity.Cliente;
import com.entity.Notificacion;
import com.entity.Tienda;

/**
 *
 * @author DAC-PC
 */
public interface INotificacion extends IDao<Notificacion, Long>{
    long ObtenerUltimo(Tienda c);
    long Obtenerultimoclien(Cliente c);
}
