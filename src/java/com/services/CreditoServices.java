/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Cliente;
import com.entity.Credito;
import com.implDao.ICredito;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class CreditoServices extends ImplDao<Credito, Long> implements ICredito, Serializable{

    @Override
    public Credito vercredito(Cliente c) {
          Credito credito = null;
        String consulta;
        try {
            consulta = "FROM Credito c WHERE c.cliente = ?1 ";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, c);
        
            List<Credito> lista =query.getResultList();
            
            if(!lista.isEmpty()){
                credito = lista.get(0);
            }
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return credito;
    }
    
}
