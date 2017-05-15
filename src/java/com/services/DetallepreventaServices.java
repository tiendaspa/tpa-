/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;


import com.dao.ImplDao;
import com.entity.Cliente;
import com.entity.Detallepreventa;
import com.entity.Preventa;
import com.entity.Producto;
import com.entity.Tienda;
import com.implDao.IDetallepreventa;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author DAC-PC
 */
public class DetallepreventaServices extends ImplDao<Detallepreventa, Long> implements IDetallepreventa,Serializable{

    @Override
    public List<Detallepreventa> verdetalle(Preventa id) {
        List<Detallepreventa> lista = new ArrayList<>();
        Detallepreventa c = null;
        String consulta;
        try {
            consulta = "FROM Detallepreventa d WHERE d.preventa = ?1";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, id);
        
            lista =query.getResultList();
            
          
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return lista;
    }

    @Override
    public List<Producto> productoporcliente(Cliente c) {
        List<Producto> listaproductos = new ArrayList<>();
            String consulta;
        try {
            consulta = "SELECT i.producto FROM Detallepreventa i WHERE i.cliente = ?1 GROUP by i.producto ORDER BY COUNT(i.producto) DESC";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, c);
        
            listaproductos =query.getResultList();
            
          
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return listaproductos;
    }

    @Override
    public List<Producto> vermascomprado(Tienda t) {

        List<Producto> listaproductomasvendido;
        String consulta;
     
        try {
            consulta = "SELECT p.producto FROM Detallepreventa p WHERE p.tienda =?1 GROUP BY p.producto ORDER BY Sum(p.cantidad) DESC ";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, t).setMaxResults(5);
            listaproductomasvendido = query.getResultList();
          
         
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return listaproductomasvendido;
    }

    @Override
    public List<Integer> cantidad(Tienda ti) {
        List<Integer> cantidadlistaproductomasvendido;
        String consulta;
     
        try {
            consulta = "SELECT Sum(pe.cantidad) FROM Detallepreventa pe WHERE pe.tienda =?1 GROUP BY pe.producto ORDER BY Sum(pe.cantidad) DESC";
            Query query = getEmf().createEntityManager().createQuery(consulta);
            query.setParameter(1, ti);
            cantidadlistaproductomasvendido = query.getResultList();
          
         
        } catch (Exception e) {
            throw  e;
        }finally{
            getEntityManagger().close();
        }
        return cantidadlistaproductomasvendido;
    }

  
    
}
