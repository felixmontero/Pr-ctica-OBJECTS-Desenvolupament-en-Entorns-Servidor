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

    Object getIdObject(int id, String name);

    void createFile(File file);

    boolean checkFile(String path);

    Object getIdFile(String hash);

    void createVersion(int FileID,int ObjectID, Date date, int version);

    Objecte getObject(int objId);

    File getFile(int fileId);
}
