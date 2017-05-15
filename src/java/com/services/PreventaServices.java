/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Cliente;
import com.entity.Preventa;
import com.entity.Tienda;
import com.implDao.IPreventa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class PreventaServices extends ImplDao<Preventa, Long> implements IPreventa,Serializable{

    @Override
    public long ObtenerUltimo() {
        long id=0;
        String consulta;
        Preventa p = null;
        try {
            consulta = "SELECT MAX(id) FROM Preventa";
            Query query = getEmf().createEntityManager().createQuery(consulta);
        
            id= (long) query.getSingleResult();
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return id;
    }

    @Override
    public List<Preventa> listarpreventa(Tienda c) {
         ArrayList<Preventa> listar = new ArrayList<>();
        String consulta = null;
        try {
            consulta = "FROM Preventa c WHERE c.tienda =?1 and c.estado =?2";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, c);
            query.setParameter(2,(byte) 0);
            listar =(ArrayList<Preventa>) query.getResultList();
            return listar;
        } catch (Exception e) {
        }finally{
            getEntityManagger().close();
        
        }
        
        
         return listar;      
    }

    @Override
    public List<Preventa> listarpreventacliente(Cliente cli) {
     ArrayList<Preventa> listar = new ArrayList<>();
        String consulta = null;
        try {
            consulta = "FROM Preventa c WHERE c.cliente =?1 and c.estado =?2";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, cli);
            query.setParameter(2,(byte) 1);
            listar =(ArrayList<Preventa>) query.getResultList();
            return listar;
        } catch (Exception e) {
        }finally{
            getEntityManagger().close();
        
        }
        
        
         return listar;    
    
    }
    
    
}
