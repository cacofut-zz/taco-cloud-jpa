/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cacodev.model.repository;

import br.com.cacodev.tacocloudjpa.model.entity.Order;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author cristianoca
 */
public interface OrderRepository extends CrudRepository<Order, Long>{
    
}
