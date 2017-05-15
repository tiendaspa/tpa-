/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Cliente;
import com.entity.Tienda;

import java.io.Serializable;
import com.implDao.ICliente;
import static com.services.TiendaServices.getEmf;
import static com.services.TiendaServices.getEntityManagger;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
/**
 *
 * @author Jcmm
 */
public class ClienteServices extends ImplDao<Cliente, Long> implements ICliente,Serializable{
     


    @Override
    public List<Cliente> listarcliente(Tienda c) {
        ArrayList<Cliente> listar = new ArrayList<>();
        String consulta = null;
        try {
            consulta = "FROM Cliente c WHERE c.tienda =?1 and c.estado =?2";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, c);
            query.setParameter(2, true);
           
            listar =(ArrayList<Cliente>) query.getResultList();
            return listar;
        } catch (Exception e) {
        }finally{
            getEntityManagger().close();
        
        }
        
        
         return listar;      
    }

    @Override
    public Cliente vercliente(Long id) {
         Cliente c = null;
        String consulta;
        try {
            consulta = "FROM Cliente c WHERE c.id = ?1 ";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, id);
        
            List<Cliente> lista =query.getResultList();
            
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

