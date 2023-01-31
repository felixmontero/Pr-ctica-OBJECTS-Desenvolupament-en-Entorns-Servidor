package com.esliceu.buckets.daos;

import com.esliceu.buckets.models.Object;

import java.util.List;

public interface ObjectDAO {
void createObject(String name, String bucket);
    void deleteObject(int id);
    void updateObject(int id, String name, String bucket);
    List<Object> getObjects(int bucket);
}
