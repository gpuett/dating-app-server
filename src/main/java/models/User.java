package models;

import java.util.Objects;

public class User {
    private String name;
    private String bio;
    private int age;
    private String orientation;
    private String imageUrl;
    private String interests;
    private int id;

    public User(String name, String bio, int age, String orientation, String imageUrl, String interests) {
        this.name = name;
        this.bio = bio;
        this.age = age;
        this.orientation = orientation;
        this.imageUrl = imageUrl;
        this.interests = interests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(bio, user.bio) &&
                Objects.equals(orientation, user.orientation) &&
                Objects.equals(imageUrl, user.imageUrl) &&
                Objects.equals(interests, user.interests);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, bio, age, orientation, imageUrl, interests, id);
    }
}
