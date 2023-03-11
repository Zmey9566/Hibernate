package org.example.dao;

import org.example.model.User;
import org.example.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.List;

public class UserDAOimpl implements UserDAO {
    private final Util util = new Util();
    // объявляем транзакцию
    Transaction transaction;

    @Override
    public void createTable() {
        // создаем настройку будующих сессий на основе которых открываются сессии (потоки)
        SessionFactory sessionFactory = util.getSession();
        try (sessionFactory) {
            // создаем поток
            Session session = sessionFactory.getCurrentSession();
            // открываем транзакцию (цепочка операций, которая или выполняется полностью или не выполнится ничего)
            session.beginTransaction();

            session.createSQLQuery("CREATE TABLE user (id BIGINT PRIMARY KEY AUTO_INCREMENT,"
                    + " name VARCHAR(40), age int)").executeUpdate();

            //завершаем транзакцию
            session.getTransaction().commit();
        }

    }

    @Override
    public void dropTable() {
        SessionFactory sessionFactory = util.getSession();
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            session.createSQLQuery("DROP TABLE user").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, int age) {
        SessionFactory sessionFactory = util.getSession();
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();

            session.save(new User(name, age));

            transaction.commit();
        } catch (PersistenceException e) { //ловим все ошибки взаимодействия с БД
            if (transaction != null) { //если в момент исполнения происходят ошибки выполнения, то все откатывается
                transaction.rollback();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = util.getSession();
        try (sessionFactory) {
            // создаем поток
            Session session = sessionFactory.getCurrentSession();
            // открываем транзакцию (цепочка операций, которая или выполняется полностью или не выполнится ничего)
            session.beginTransaction();

            User user;
            user = (User) session.load(User.class, id);
            session.delete(user);
            session.flush();

            //завершаем транзакцию
            session.getTransaction().commit();
        } catch (PersistenceException e) { //ловим все ошибки взаимодействия с БД
            if (transaction != null) { //если в момент исполнения происходят ошибки выполнения, то все откатывается
                transaction.rollback();

            }
        }
    }

    @Override
    public void getUserById(long id) {
        SessionFactory sessionFactory = util.getSession();
        try (sessionFactory) {
            // создаем поток
            Session session = sessionFactory.getCurrentSession();
            // открываем транзакцию (цепочка операций, которая или выполняется полностью или не выполнится ничего)
            transaction = session.beginTransaction();

            System.out.println(session.get(User.class, id));

            //завершаем транзакцию
            transaction.commit();
        } catch (PersistenceException b) { //ловим все ошибки взаимодействия с БД
            if (transaction != null) { //если в момент исполнения происходят ошибки выполнения, то все откатывается
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = util.getSession();
        try (sessionFactory) {
            // создаем поток
            Session session = sessionFactory.getCurrentSession();
            // открываем транзакцию (цепочка операций, которая или выполняется полностью или не выполнится ничего)
            transaction = session.beginTransaction();

            List<User> users = (List<User>) session.createQuery("FROM User").list();
            //завершаем транзакцию
            transaction.commit();
            System.out.println(users);
            return users;
        } catch (PersistenceException c) { //ловим все ошибки взаимодействия с БД
            if (transaction != null) { //если в момент исполнения происходят ошибки выполнения, то все откатывается
                transaction.rollback();
            }
        }
        return getAllUsers();
    }

    @Override
    public void cleanUserTable() {
        SessionFactory sessionFactory = util.getSession();
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();

            session.getTransaction().commit();
        }
    }
}