/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Cliente;
import com.entity.Tienda;
import com.implDao.IUsuario;
import static com.services.TiendaServices.getEmf;
import static com.services.TiendaServices.getEntityManagger;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class UsuarioServices extends ImplDao<Cliente, Long> implements IUsuario, Serializable{

    @Override
    public Cliente iniciarsesion(Cliente c) {
        Cliente clien = null;
        String consulta;
        try {
            consulta = "FROM Cliente c WHERE c.usuario = ?1 and c.contrasena = ?2";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, c.getUsuario());
            query.setParameter(2, c.getContrasena());
            List<Cliente> lista =query.getResultList();
            
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
