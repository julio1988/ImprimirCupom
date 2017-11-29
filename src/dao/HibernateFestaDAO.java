/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.HibernateGenericDAO.entityManager;
import entidades.Festa;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Julio
 */
public class HibernateFestaDAO extends HibernateGenericDAO<Festa, Long> {

    public HibernateFestaDAO() {
        super(Festa.class);
    }

    public List<Object[]> buscarExtratoPorFesta(Festa festa) {
        String sql = "Select item.produto, "
                + "          item.precoUnitario,"
                + "    sum(item.quantidade), "
                + "    sum(item.quantidade * item.precoUnitario)  "
                + "From Venda v  "
                + "inner join itemvenda item on item.venda_id = v.id "
                + "where v.festa_id = :id "
                + "GROUP BY "
                + "   item.produto, item.precoUnitario"
                + " order by item.produto ";
        Query q = entityManager.createNativeQuery(sql).setParameter("id", festa.getId());
        return q.getResultList();
    }
}
