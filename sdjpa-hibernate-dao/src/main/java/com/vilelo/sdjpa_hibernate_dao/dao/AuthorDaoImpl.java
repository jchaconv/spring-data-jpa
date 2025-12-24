package com.vilelo.sdjpa_hibernate_dao.dao;

import com.vilelo.sdjpa_hibernate_dao.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;

    public AuthorDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Author getAuthorById(Long id) {
        EntityManager entityManager = getEntityManager();
        Author author = entityManager.find(Author.class, id);
        entityManager.close();
        return author;
    }

    @Override
    public Author getAuthorByName(String firstName, String lastName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a " +
                "WHERE a.firstName = :first_name AND a.lastName = :last_name", Author.class);
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);
        Author author = query.getSingleResult();
        entityManager.close();
        return author;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.joinTransaction(); // Is a transaction previously crated?
            entityManager.merge(author);
            entityManager.flush();
            entityManager.clear();  // to clear the Hibernate cache
            return entityManager.find(Author.class, author.getId());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteAuthorById(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
