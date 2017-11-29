/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.ItemVenda;

/**
 *
 * @author Julio
 */
public class HibernateItemVendaDAO extends HibernateGenericDAO<ItemVenda, Long> {

    public HibernateItemVendaDAO() {
        super(ItemVenda.class);
    }
}
