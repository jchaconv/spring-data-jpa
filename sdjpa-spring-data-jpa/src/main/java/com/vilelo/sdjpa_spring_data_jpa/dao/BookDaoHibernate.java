package com.vilelo.sdjpa_spring_data_jpa.dao;

import com.vilelo.sdjpa_spring_data_jpa.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BookDaoHibernate implements BookDao {

    private final EntityManagerFactory entityManagerFactory;

    public BookDaoHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {
        EntityManager entityManager = getEntityManager();
        try {

            String hql = "SELECT b FROM Book b order by b.title " +
                    pageable.getSort().getOrderFor("title").getDirection().name();

            TypedQuery<Book> query = entityManager.createQuery(hql, Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        return List.of();
    }

    @Override
    public List<Book> findAllBooks() {
        return List.of();
    }

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public Book findBookByTitle(String title) {
        return null;
    }

    @Override
    public Book saveNewBook(Book book) {
        return null;
    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
