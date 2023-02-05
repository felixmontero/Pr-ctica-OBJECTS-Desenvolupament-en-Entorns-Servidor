package com.esliceu.buckets.daos;

import com.esliceu.buckets.models.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class BucketDAOImpl implements BucketDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Bucket> bucketRowMapper = (rs, rn) -> {
        Bucket bucket = new Bucket();
        bucket.setId(rs.getInt("id"));
        bucket.setNom(rs.getString("nom"));
        bucket.setOwner(rs.getString("owner"));
        bucket.setCreateDate(rs.getDate("createDate"));
        return bucket;
    };

    @Override
    public void createBucket(String nom, String owner, Date createDate) {
        jdbcTemplate.update("INSERT INTO bucket (nom, owner, createDate) VALUES (?,?,?)",
                nom, owner, createDate);
    }

    @Override
    public void deleteBucket(int id) {
        jdbcTemplate.update("DELETE FROM bucket WHERE id = (?)", id);
    }

    @Override
    public void updateBucket(int id, String name, String owner, Date createDate){
        jdbcTemplate.update("UPDATE bucket SET nom = (?), owner = (?), createDate = (?) WHERE id = (?)",
                name, owner, createDate, id);
    }

    @Override
    public List<Bucket> getBuckets(String nickname) {
        return jdbcTemplate.query("SELECT * FROM bucket WHERE owner = (?)",
                new Object[]{nickname}, bucketRowMapper);
    }

    @Override
    public Bucket getBucketByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM bucket WHERE nom = (?)",
                new Object[]{name}, bucketRowMapper);
    }

    @Override
    public List<Bucket> getBucketByNameList(String name) {
        return jdbcTemplate.query("SELECT * FROM bucket WHERE nom = (?)",
                new Object[]{name}, bucketRowMapper);
    }

    @Override
    public void deleteBucketByNom(String nom) {
        jdbcTemplate.update("DELETE FROM bucket WHERE nom = (?)", nom);
    }

}
