package com.esliceu.buckets.daos;

import com.esliceu.buckets.models.Bucket;

import java.util.Date;
import java.util.List;

public interface BucketDAO {

    void createBucket(String name, String owner, Date createDate);

    void deleteBucket(int id);

    void updateBucket(int id, String name, String owner, Date createDate);

    List<Bucket> getBuckets(String nickname);

    Bucket getBucketByName(String name);

    List<Bucket> getBucketByNameList(String name);

    void deleteBucketByNom(String nom);
}
