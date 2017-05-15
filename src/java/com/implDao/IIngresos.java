/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implDao;

import com.dao.IDao;
import com.entity.Cliente;
import com.entity.Ingresos;
import com.entity.Tienda;
import java.util.List;

/**
 *
 * @author DAC-PC
 */
public interface IIngresos extends IDao<Ingresos, Long>{
    double obteneringresos(Tienda t);
    List<Cliente> vermascomprador(Tienda t);
}
