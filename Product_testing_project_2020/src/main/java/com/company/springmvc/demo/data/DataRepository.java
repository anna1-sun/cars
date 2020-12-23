package com.company.springmvc.demo.data;

import com.company.springmvc.demo.dto.ProductSearchDto;
import lombok.NonNull;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class DataRepository {
    private static SessionFactory factory;

    public DataRepository() {
        try {
            factory = new Configuration().
                    configure().
                    addAnnotatedClass(Product.class).
                    addAnnotatedClass(Category.class).
                    addAnnotatedClass(Bacteria.class).
                    addAnnotatedClass(Limit.class).
                    addAnnotatedClass(TestResultItem.class).
                    buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Iterable<Product> getProducts() {
        var session = factory.openSession();

        try {
            return session.createQuery("FROM Product").list();
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    public Iterable<Product> getCategories() {
        var session = factory.openSession();

        try {
            return session.createQuery("FROM Category").list();
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    public Category getCategory(int id) {
        var session = factory.openSession();

        try {
            return session.get(Category.class, id);
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return null;
    }

    public Iterable<Product> getBacterias() {
        var session = factory.openSession();

        try {
            return session.createQuery("FROM Bacteria").list();
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    public Iterable<Product> getLimits() {
        var session = factory.openSession();

        try {
            return session.createQuery("FROM Limit").list();
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }


    public Product getProductById(int id) {
        var session = factory.openSession();

        try {
            return session.get(Product.class, id);
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return null;
    }

    public void updateProduct(@NonNull Object item) {
        var session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(item);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println(exception);
        } finally {
            session.close();
        }

    }
    public void addProduct(@NonNull Product product) {
        var session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(product);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println(exception);
        } finally {
            session.close();
        }
    }

    public void deleteProduct(int id) {
        var session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            var product = session.get(Product.class, id);
            if(product != null){
                session.delete(product);
            }
            tx.commit();
        } catch (HibernateException exception) {
            if(tx != null) {
                tx.rollback();
            }
            System.err.println(exception);
        } finally {
            session.close();
        }
    }
    public Iterable<Product> getProducts(ProductSearchDto searchDto) {
        var session = factory.openSession();

        var code = searchDto.getCode();
        var name = searchDto.getName();

        try {
            //return session.createQuery("FROM Product").list();
            var sql = "FROM Product";

            if(!code.isBlank() || !name.isBlank()){
                sql += " where ";
            }

            if(!code.isBlank()){
                sql += " code = :search_code";
            }

            if (!name.isBlank()){

                if(!code.isBlank()){
                    sql += " and ";
                }

                sql += " name = :search_name";
            }

            var query = session.createQuery(sql);

            if(!code.isBlank()){
                query.setParameter("search_code", code);
            }
            if(!name.isBlank()){
                query.setParameter("search_name", name);
            }
            return query.list();

        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    public void addResult(@NonNull Product product) {
        var session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(product);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println(exception);
        } finally {
            session.close();
        }
    }
    public Iterable<Limit> getCategoryLimit(int categoryId) {
        var session = factory.openSession();

        try {
            var sql = "FROM Limit where category_id = :catId";
            var query = session.createQuery(sql);
            query.setParameter("catId", categoryId);
            var result = query.list();
            return result;

        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }
    public Iterable<TestResultItem> getTestResultItems(int productId) {
        var session = factory.openSession();

        try {
            var sql = "FROM TestResultItem where product_id = :prodId";
            var query = session.createQuery(sql);
            query.setParameter("prodId", productId);
            var result = query.list();
            return result;

        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }
}
