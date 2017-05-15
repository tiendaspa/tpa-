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
import com.entity.Producto;
import com.entity.Tienda;
import com.implDao.IProducto;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class ProductoServices extends ImplDao<Producto, Long> implements IProducto, Serializable{

    @Override
    public long ObtenerUltimo() {

        long id=0;
        String consulta;
        Producto p = null;
        try {
            consulta = "SELECT MAX(id) FROM Producto";
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
    public List<Producto> listarproducto(Tienda c){
        ArrayList<Producto> listar = new ArrayList<>();
        String consulta = null;
        try {
            consulta = "FROM Producto c WHERE c.tienda =?1";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, c);
           
            listar =(ArrayList<Producto>) query.getResultList();
            return listar;
        } catch (Exception e) {
        }finally{
            getEntityManagger().close();
        
        }
        
        
         return listar;      
    }

    @Override
    public Producto verproducto(Long id) {
        Producto c = null;
        String consulta;
        try {
            consulta = "FROM Producto c WHERE c.id = ?1 ";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, id);
        
            List<Producto> lista =query.getResultList();
            
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
