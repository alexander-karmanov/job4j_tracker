package ru.job4j.tracker;

import ru.job4j.tracker.Item;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    private void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try {
            PreparedStatement statement = cn.prepareStatement("INSERT INTO items(name, created) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try {
            PreparedStatement statement = cn.prepareStatement("UPDATE items SET name = ? WHERE id = ?;");
            statement.setString(1, item.getName());
            statement.setInt(2, id);
            statement.execute();
            if (statement.getUpdateCount() > 0) {
                rsl = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rsl;
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = cn.prepareStatement("DELETE FROM items WHERE id = ?;");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> allItems = new ArrayList<>();
         try {
            PreparedStatement statement = cn.prepareStatement("SELECT * FROM items;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allItems.add(new Item(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getTimestamp("created").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allItems;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> itemsByName = new ArrayList<>();
        try {
            PreparedStatement statement = cn.prepareStatement("SELECT * FROM items WHERE name = ?;");
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                itemsByName.add(new Item(
                        resultSet.getInt(1),
                        resultSet.getString(key),
                        resultSet.getTimestamp("created").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemsByName;
    }

    @Override
    public Item findById(int id) {
        Item it = null;
        try {
            PreparedStatement statement = cn.prepareStatement("SELECT * FROM items WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                it = new Item(
                        resultSet.getInt(1),
                        resultSet.getString(id),
                        resultSet.getTimestamp("created").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return it;
    }
}
