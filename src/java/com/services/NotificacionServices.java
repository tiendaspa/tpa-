/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Cliente;
import com.entity.Notificacion;
import com.entity.Tienda;
import com.implDao.INotificacion;
import java.io.Serializable;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class NotificacionServices extends ImplDao<Notificacion, Long> implements INotificacion, Serializable{
    @Override
    public long ObtenerUltimo(Tienda c) {

        long id=0;
        String consulta;
        
        Notificacion p = null;
        try {
            consulta = "SELECT MAX(id) FROM Notificacion c WHERE c.tienda =?1";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, c);
            id= (long) query.getSingleResult();
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return id;
    }

    @Override
    public long Obtenerultimoclien(Cliente c) {
           long id=0;
        String consulta;
        
        Notificacion p = null;
        try {
            consulta = "SELECT MAX(id) FROM Notificacion c WHERE c.cliente =?1";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, c);
            id= (long) query.getSingleResult();
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return id;
    }
}
