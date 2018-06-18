package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getName() {
        User user = setupUser();
        assertEquals("Kim", user.getName());
    }

    @Test
    public void setName() {
        User user = setupUser();
        user.setName("John");
        assertEquals("John", user.getName());
    }

    @Test
    public void getBio() {
        User user = setupUser();
        assertEquals("I'm from Philly", user.getBio());
    }

    @Test
    public void setBio() {
        User user = setupUser();
        user.setBio("West Philly");
        assertEquals("West Philly", user.getBio());
    }

    @Test
    public void getAge() {
        User user = setupUser();
        assertEquals(24, user.getAge());
    }

    @Test
    public void setAge() {
        User user = setupUser();
        user.setAge(30);
        assertEquals(30, user.getAge());
    }

    @Test
    public void getOrientation() {
        User user = setupUser();
        assertEquals("F4M", user.getOrientation());
    }

    @Test
    public void setOrientation() {
        User user = setupUser();
        user.setOrientation("M4F");
        assertEquals("M4F", user.getOrientation());
    }

    @Test
    public void getImageUrl() {
        User user = setupUser();
        assertEquals("images.google.com", user.getImageUrl());
    }

    @Test
    public void getInterests() {
        User user = setupUser();
        assertEquals("TDD", user.getInterests());
    }

    @Test
    public void setInterests() {
        User user = setupUser();
        user.setInterests("OOP");
        assertEquals("OOP", user.getInterests());
    }

    @Test
    public void setId() {
        User user = setupUser();
        user.setId(1);
        assertEquals(1, user.getId());
    }

    //helper model
    public User setupUser() {
        return new User("Kim", "I'm from Philly", 24, "F4M", "images.google.com", "TDD");
    }
}