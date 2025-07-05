package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        item.setId(id);
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public void delete(int id) {
        Item item = findById(id);
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
        }  catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        List<Item> result = new ArrayList<>();
        try {
            session.beginTransaction();
            result = session.createQuery("from ru.job4j.tracker.Item", Item.class).list();
            session.getTransaction().commit();
        }  catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        List<Item> result = new ArrayList<>();
        try {
            session.beginTransaction();
            result = session.createQuery(
                            "from Item i where i.name like :pattern", Item.class)
                    .setParameter("pattern", "%" + key + "%")
                    .list();
            session.getTransaction().commit();
        }  catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        Item result = null;
        try {
            session.beginTransaction();
            result = session.get(Item.class, id);
            session.getTransaction().commit();
        }  catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}