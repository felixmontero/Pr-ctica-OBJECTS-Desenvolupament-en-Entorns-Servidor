package com.esliceu.buckets.models;

public class Object {

    private int id;
    private String name;
    private int bucket;

    public Object() {
    }
    public Object(int id, String name, int bucket) {
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
