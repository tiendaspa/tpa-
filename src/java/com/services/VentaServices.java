/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Venta;
import com.implDao.IVenta;
import java.io.Serializable;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class VentaServices extends ImplDao<Venta, Long> implements IVenta, Serializable{

    @Override
    public long ObtenerUltimo() {
            long id=0;
        String consulta;
        Venta p = null;
        try {
            consulta = "SELECT MAX(id) FROM Venta";
            Query query = getEmf().createEntityManager().createQuery(consulta);
        
            id= (long) query.getSingleResult();
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return id;
    }
    
            
      }
