package com.esliceu.buckets.services;

import com.esliceu.buckets.daos.BucketDAO;
import com.esliceu.buckets.daos.ObjecteDAO;
import com.esliceu.buckets.models.Bucket;
import com.esliceu.buckets.models.Objecte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjecteService {
    @Autowired
    ObjecteDAO objectDAO;
    @Autowired
    BucketDAO bucketDAO;

    public List<Objecte> getObjects(String bucket) {
        System.out.println("Bucket: " + bucket);
        Bucket bucket1= bucketDAO.getBucketByName(bucket);
        //System.out.println(bucket1.getNom());
        return objectDAO.getObjects(bucket1.getId());

    }
}
