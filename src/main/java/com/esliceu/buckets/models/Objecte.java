package com.esliceu.buckets.models;

public class Objecte {

    private int id;
    private String name;
    private String user;
    private int bucket;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Objecte() {
    }
    public Objecte(int id, String name, int bucket) {
        this.id = id;
        this.name = name;
        this.bucket = bucket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBucket() {
        return bucket;
    }

    public void setBucket(int bucket) {
        this.bucket = bucket;
    }
}
