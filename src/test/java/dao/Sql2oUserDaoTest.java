package dao;

import models.User;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private static Connection conn;
    private static Sql2oUserDao userDao;


    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/dating_app_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        userDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add() {
        User user = setupUser();
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void getAllForNoEntry() {
        assertEquals(0, userDao.getAll().size());
    }

    @Test
    public void getAll() {
        User user1 = setupUser();
        User user2 = setupUser();
        assertEquals(2, userDao.getAll().size());
    }

    @Test
    public void findById() {
        User user1 = setupUser();
        User user2 = setupUser();
        assertEquals(user1, userDao.findById(user1.getId()));
    }

    @Test
    public void update() {
        User user = setupUser();
        userDao.update(user.getId(), "John", "I'm from West Philly", 35, "M4M", "imagewuser.com", "OOP");
        User foundUser = userDao.findById(user.getId());
        assertNotEquals("Kim", foundUser.getName());
    }

    @Test
    public void deleteById() {
        User user1 = setupUser();
        User user2 = setupUser();
        userDao.deleteById(user1.getId());
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void clearAll() {
        User user1 = setupUser();
        User user2 = setupUser();
        userDao.clearAll();
        assertEquals(0, userDao.getAll().size());
    }

    //helper
    public User setupUser() {
        User user = new User("Kim", "I'm from Philly", 24, "F4M", "images.google.com", "TDD");
        userDao.add(user);
        return user;
    }
}