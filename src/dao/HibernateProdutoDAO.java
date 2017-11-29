/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Produto;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Julio
 */
public class HibernateProdutoDAO extends HibernateGenericDAO<Produto, Long> {

    public HibernateProdutoDAO() {
        super(Produto.class);
    }

    public List<Produto> buscarProdutosAtivos() {
        String hql = "Select p From Produto p where p.ativo = :ativo order by p.nome";
        Query q = entityManager.createQuery(hql, Produto.class).setParameter("ativo", Boolean.TRUE);
        return q.getResultList();
    }

    public boolean hasProdutosAtivos() {
        try {
            return !buscarProdutosAtivos().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
