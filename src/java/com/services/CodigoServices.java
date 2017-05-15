/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Cliente;
import com.entity.CodigoSesion;
import com.entity.Tienda;
import com.implDao.ICodigoSesion;
import static com.services.TiendaServices.getEmf;
import static com.services.TiendaServices.getEntityManagger;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class CodigoServices extends ImplDao<CodigoSesion, Long> implements ICodigoSesion, Serializable{

    @Override
    public CodigoSesion iniciarsesion(String codigo) {
         CodigoSesion codigosesion = null;
        String consulta;
        try {
            consulta = "FROM CodigoSesion c WHERE c.codigo = ?1 ";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, codigo);
          
            List<CodigoSesion> lista =query.getResultList();
            
            if(!lista.isEmpty()){
                codigosesion = lista.get(0);
            }
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return codigosesion;
    
    
    }

    @Override
    public Cliente comprobarsesion(Long id) {
       Cliente cliente = null;
       String consulta;
       try {
            consulta = "FROM Cliente p WHERE p.id = ?1";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, id);
            
          
            List<Cliente> lista =query.getResultList();
            
            if(!lista.isEmpty()){
                cliente = lista.get(0);
            }
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return cliente;
    
    }
    }

