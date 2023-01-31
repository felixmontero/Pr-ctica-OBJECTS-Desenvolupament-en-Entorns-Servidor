package com.esliceu.buckets.daos;

import com.esliceu.buckets.models.Objecte;

import java.util.List;

public interface ObjectDAO {
void createObject(String name, String bucket);
    void deleteObject(int id);
    void updateObject(int id, String name, String bucket);
    List<Objecte> getObjects(int bucket);
}
