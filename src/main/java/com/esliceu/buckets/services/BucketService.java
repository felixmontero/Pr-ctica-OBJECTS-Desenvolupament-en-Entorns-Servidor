package com.esliceu.buckets.services;

import com.esliceu.buckets.daos.BucketDAO;
import com.esliceu.buckets.daos.ObjectDAO;
import com.esliceu.buckets.models.Bucket;
import com.esliceu.buckets.models.Objecte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class BucketService {
    @Autowired
    BucketDAO bucketDAO;
    @Autowired
    ObjectDAO objectDAO;

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
        if (uniqueName(name)) {
            bucketDAO.createBucket(name, nickname, new Date());
        }
    }

    private boolean uniqueName(String name) {
        List<Bucket> buckets = bucketDAO.getBucketByName(name);
        for (Bucket bucket : buckets) {
            if (bucket.getNom().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public List<Objecte> getObjects(int bucket) {
        return objectDAO.getObjects(bucket);
    }
}
