package com.vilelo.sdjpa_hibernate_dao.dao;

import com.vilelo.sdjpa_hibernate_dao.domain.Author;
import com.vilelo.sdjpa_hibernate_dao.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    private final EntityManagerFactory entityManagerFactory;

    public BookDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public List<Book> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Book> typedQuery = entityManager.createNamedQuery("book_find_all", Book.class);
            return typedQuery.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Book findByISBN(String isbn) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b " +
                "WHERE b.isbn = :isbn", Book.class);
        query.setParameter("isbn", isbn);
        Book book = query.getSingleResult();
        entityManager.close();
        return book;
    }

    @Override
    public Book getById(Long id) {
        EntityManager entityManager = getEntityManager();
        Book book = entityManager.find(Book.class, id);
        entityManager.close();
        return book;
    }

    @Override
    public Book findBookByTitle(String title) {
        EntityManager entityManager = getEntityManager();
        /*TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b " +
                "WHERE b.title = :title", Book.class);*/

        TypedQuery<Book> query = entityManager.createNamedQuery("find_by_title", Book.class);

        query.setParameter("title", title);
        Book book = query.getSingleResult();
        entityManager.close();
        return book;
    }

    @Override
    public Book saveNewBook(Book book) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(book);
            entityManager.flush();
            entityManager.clear();
            Book savedBook = entityManager.find(Book.class, book.getId());
            entityManager.getTransaction().commit();
            entityManager.close();
            return savedBook;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteBookById(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
