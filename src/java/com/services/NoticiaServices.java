/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Noticia;
import com.implDao.INoticia;
import java.io.Serializable;

/**
 *
 * @author DAC-PC
 */
public class NoticiaServices extends ImplDao<Noticia, Long> implements INoticia, Serializable{
    
}
