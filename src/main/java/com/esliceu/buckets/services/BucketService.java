package com.esliceu.buckets.services;

import com.esliceu.buckets.daos.BucketDAO;
import com.esliceu.buckets.models.Bucket;
import com.esliceu.buckets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class BucketService {
    @Autowired
    BucketDAO bucketDAO;

    public void updateBucket (int id,String name, String owner, Date createDate){
        bucketDAO.updateBucket(id,name, owner, createDate);
    }
    public void deleteBucket (int id){
        bucketDAO.deleteBucket(id);
    }

    public List<Bucket> getBuckets(String nickname) {
       return bucketDAO.getBuckets(nickname);
    }

    public void createBucket(String name, String nickname) {
        bucketDAO.createBucket(name, nickname, new Date());
    }

    public List<Object> getObjects(int bucket) {
        return bucketDAO.getObjects(bucket);
    }
}
