package ru.job4j.tracker.store;

import org.junit.jupiter.api.*;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SqlTracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class SqlTrackerTest {
    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenAddAndCheckResultListLength() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = new Item("Volvo");
        Item second = new Item("Mercedes");
        Item third = new Item("Mazda");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        List<Item> result = tracker.findAll();
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void whenReplaceItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item itemOne = new Item();
        itemOne.setName("Audi");
        tracker.add(itemOne);
        int id = itemOne.getId();
        Item itemTwo = new Item();
        itemTwo.setName("Volkswagen");
        tracker.replace(id, itemTwo);
        assertThat(tracker.findById(id).getName()).isEqualTo("Volkswagen");
    }

    @Test
    public void whenDeleteItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("car");
        tracker.add(item);
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenFindAllItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item itemOne = new Item("Hyundai");
        Item itemTwo = new Item("Kia");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        Item resultOne = tracker.findAll().get(0);
        Item resultTwo = tracker.findAll().get(1);
        assertThat(resultOne.getName()).isEqualTo(itemOne.getName());
        assertThat(resultTwo.getName()).isEqualTo(itemTwo.getName());
    }

    @Test
    public void whenFindById() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("Audi");
        Item audi = tracker.add(item);
        Item result = tracker.findById(audi.getId());
        assertThat(result.getName()).isEqualTo(audi.getName());
    }

      @Test
        public void whenFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("Hyundai");
        Item itemTwo = new Item("Kia");
        tracker.add(item);
        tracker.add(itemTwo);
        tracker.add(new Item("Hyundai"));
        List<Item> result = tracker.findByName(item.getName());
        assertThat(result.get(1).getName()).isEqualTo(item.getName());
    }
}
