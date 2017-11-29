/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Julio
 */
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class HibernateGenericDAO<T, I extends Serializable> {

    protected static EntityManager entityManager;
    private Class<T> classeEntidade;

    protected HibernateGenericDAO() {
        entityManager = ConnectionFactory.getEntityManager();
    }

    protected HibernateGenericDAO(Class<T> classeEntidade) {
        this();
        this.classeEntidade = classeEntidade;
    }

    public T salvar(T entity) {
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        entityManager.persist(entity);
        entityManager.flush();
        t.commit();
        return entity;
    }

    public T atualizar(T entity) {
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        entityManager.merge(entity);
        entityManager.flush();
        t.commit();
        return entity;
    }

    public void remover(I id) {
        T entity = encontrar(id);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        T mergedEntity = entityManager.merge(entity);
        entityManager.remove(mergedEntity);
        entityManager.flush();
        tx.commit();
    }

    public List<T> getList() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(classeEntidade);
        query.from(classeEntidade);
        return entityManager.createQuery(query).getResultList();
    }

    public T encontrar(I id) {
        return entityManager.find(classeEntidade, id);
    }

    public List<T> listaFiltrando(String s, String... atributos) {
        String hql = "from " + classeEntidade.getSimpleName() + " obj where ";
        for (String atributo : atributos) {
            hql += "lower(obj." + atributo + ") like :filtro OR ";
        }
        hql = hql.substring(0, hql.length() - 3);
        Query q = entityManager.createQuery(hql);
        q.setParameter("filtro", "%" + s.toLowerCase().trim() + "%");
        return q.getResultList();
    }
}
