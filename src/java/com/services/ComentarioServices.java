/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Comentario;
import com.entity.Tienda;
import com.implDao.IComentario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class ComentarioServices extends ImplDao<Comentario, Long> implements IComentario, Serializable{
     
    
    @Override
    public List<Comentario> listarcomentario(Tienda c) {
        ArrayList<Comentario> listar = new ArrayList<>();
        String consulta = null;
        try {
            consulta = "FROM Comentario c WHERE c.tienda =?1";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            
            query.setParameter(1, c);
           
            listar =(ArrayList<Comentario>) query.getResultList();
            return listar;
        } catch (Exception e) {
        }finally{
            getEntityManagger().close();
        
        }
        
        
         return listar;    
    }

    @Override
    public Comentario vercomentario(Long id) {
         Comentario c = null;
        String consulta;
        try {
            consulta = "FROM Comentario c WHERE c.id = ?1 ";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, id);
        
            List<Comentario> lista =query.getResultList();
            
            if(!lista.isEmpty()){
                c = lista.get(0);
            }
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return c;
    }
    
}
