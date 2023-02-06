package com.esliceu.buckets.daos;

import com.esliceu.buckets.models.File;
import com.esliceu.buckets.models.Objecte;

import java.util.Date;
import java.util.List;

public interface ObjecteDAO {

    void createObject(Objecte object);

    void deleteObject(int id);
    void updateObject(int id, String name, String bucket);
    List<Objecte> getObjects(int bucket);

    boolean checkObject(int bucket, String name);

    int getIdObject(int id, String name);

    void createFile(File file);

    boolean checkFile(String path);

    Object getIdFile(String hash);

    void createVersion(int id, Date date, Object idFile,int version);
}
