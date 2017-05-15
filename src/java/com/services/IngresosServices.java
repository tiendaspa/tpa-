/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Cliente;
import com.entity.Ingresos;
import com.entity.Tienda;
import com.implDao.IIngresos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class IngresosServices extends ImplDao<Ingresos, Long> implements IIngresos, Serializable{

    @Override
    public double obteneringresos(Tienda t) {
        double id=0;
        double total=0;
        String consulta;
     
        try {
            consulta = "SELECT SUM(total) FROM Ingresos i WHERE i.tienda = ?1";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, t);
            id= (double) query.getSingleResult();
            if(id > 0){
                total=id;
                return total;
                        
            }
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return id;
    }

    @Override
    public List<Cliente> vermascomprador(Tienda t) {
  
        List<Cliente> listacliente;
        String consulta;
     
        try {
            consulta = "SELECT i.cliente FROM Ingresos i WHERE i.tienda = ?1 GROUP by i.cliente ORDER BY COUNT(i.cliente) DESC ";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, t).setMaxResults(1);
            listacliente = query.getResultList();
          
         
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return listacliente;
    }
    
}
