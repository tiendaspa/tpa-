/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import static com.dao.ImplDao.getEmf;
import static com.dao.ImplDao.getEntityManagger;
import com.entity.Cliente;
import com.entity.Debito;
import com.entity.Tienda;
import com.implDao.IDebito;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class DebitoServices extends ImplDao<Debito, Long> implements IDebito, Serializable {

    @Override
    public List<Debito> vercreditos(Tienda t) {
       ArrayList<Debito> listar = new ArrayList<>();
        String consulta = null;
        try {
            consulta = "FROM Debito c WHERE c.tienda =?1  and c.estado =?2";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, t);
            query.setParameter(2, "Pendiente");
        
           
            listar =(ArrayList<Debito>) query.getResultList();
            return listar;
        } catch (Exception e) {
        }finally{
            getEntityManagger().close();
        
        }
        
        
         return listar;  
    }
    
}
