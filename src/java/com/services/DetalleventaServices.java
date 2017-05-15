/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import static com.dao.ImplDao.getEmf;
import static com.dao.ImplDao.getEntityManagger;
import com.entity.Detallepreventa;
import com.entity.Detalleventa;
import com.entity.Venta;
import com.implDao.IDetalleventa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class DetalleventaServices extends ImplDao<Detalleventa, Long> implements IDetalleventa, Serializable{

    @Override
    public List<Detalleventa> verdetalleventa(Venta c) {
      List<Detalleventa> lista = new ArrayList<>();
        Detalleventa d = null;
        String consulta;
        try {
            consulta = "FROM Detalleventa d WHERE d.venta = ?1";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, c);
        
            lista =query.getResultList();
            
          
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return lista;
    }
    
}
