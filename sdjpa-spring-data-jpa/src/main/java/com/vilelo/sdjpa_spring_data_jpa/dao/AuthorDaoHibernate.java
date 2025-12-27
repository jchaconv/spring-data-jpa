package com.vilelo.sdjpa_spring_data_jpa.dao;

import com.vilelo.sdjpa_spring_data_jpa.domain.Author;
import com.vilelo.sdjpa_spring_data_jpa.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AuthorDaoHibernate implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;

    public AuthorDaoHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Author> findAllAuthorsByLastName(String lastName, Pageable pageable) {
        EntityManager entityManager = getEntityManager();
        try {

            String hql = "SELECT a FROM Author a WHERE a.lastName =:lastName";

            if(pageable.getSort().getOrderFor("myfirstname") != null) {
                hql += " ORDER BY a.firstName " +
                        pageable.getSort().getOrderFor("myfirstname").getDirection().name();
            }

            TypedQuery<Author> query = entityManager.createQuery(hql, Author.class);
            query.setParameter("lastName", lastName);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();

        } finally {
            entityManager.close();
        }
    }

    @Override
    public Author getById(Long id) {
        return null;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
