/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implDao;

import com.dao.IDao;
import com.entity.Cliente;
import com.entity.Tienda;
import java.util.List;

/**
 *
 * @author Jcmm
 */
public interface ICliente extends IDao<Cliente, Long>{
    List<Cliente>listarcliente(Tienda c);
    Cliente vercliente(Long id);
}
