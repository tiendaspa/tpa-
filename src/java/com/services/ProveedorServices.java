/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import static com.dao.ImplDao.getEmf;
import static com.dao.ImplDao.getEntityManagger;
import com.entity.Producto;
import com.entity.Proveedor;
import com.entity.Tienda;
import com.implDao.IProveedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class ProveedorServices extends ImplDao<Proveedor, Long> implements IProveedor,Serializable{
      

    @Override
    public List<Proveedor> listarproveedor(Tienda c) {
        
        ArrayList<Proveedor> listar = new ArrayList<>();
        String consulta = null;
        try {
            consulta = "FROM Proveedor c WHERE c.tienda =?1";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, c);
           
            listar =(ArrayList<Proveedor>) query.getResultList();
            return listar;
        } catch (Exception e) {
        }finally{
            getEntityManagger().close();
        
        }
        
                
        
         return listar;
    }

    @Override
    public Proveedor verproveedor(Long id) {
        Proveedor c = null;
        String consulta;
        try {
            consulta = "FROM Producto c WHERE c.id = ?1 ";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, id);
        
            List<Proveedor> lista =query.getResultList();
            
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
