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
import com.entity.SesionesCliente;
import com.implDao.ISesionesCliente;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class SesionesClienteServices extends ImplDao<SesionesCliente, Long> implements ISesionesCliente,Serializable{

    @Override
    public SesionesCliente validarsesion(Cliente c) {
       SesionesCliente clien = null;
        String consulta;
        try {
            consulta = "FROM SesionesCliente c WHERE c.cliente = ?1";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, c);
     
            List<SesionesCliente> lista =query.getResultList();
            
            if(!lista.isEmpty()){
                clien = lista.get(0);
            }
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return clien;
    }

}
