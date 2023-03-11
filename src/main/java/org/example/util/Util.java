package org.example.util;

import org.example.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Util {
    public SessionFactory getSession() {
        //Создаем класс конфигурацию для объекта User
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        //указываем гиберу что нужно создать сессию на основе конфигурации
        return configuration.buildSessionFactory();

    }
}
