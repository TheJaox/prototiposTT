package com.ttest.testwap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;

    static{
        try {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
            SESSION_FACTORY = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    public static Session openSession() {
        return SESSION_FACTORY.openSession();
    }
}
