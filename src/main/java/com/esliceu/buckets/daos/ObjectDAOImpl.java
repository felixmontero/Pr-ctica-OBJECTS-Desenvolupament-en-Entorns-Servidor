package com.esliceu.buckets.daos;

import com.esliceu.buckets.models.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ObjectDAOImpl implements ObjectDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Object> bucketRowMapper = (rs, rn) -> {
        Object object = new Object();
        object.setId(rs.getInt("id"));
        object.setName(rs.getString("nom"));
        object.setBucket(rs.getInt("bucket"));
        return object;
    };

    @Override
    public void createObject(String name, String bucket) {
        jdbcTemplate.update("INSERT INTO object (nom, bucket) VALUES (?,?)",
                name, bucket);
    }

    @Override
    public void deleteObject(int id) {
        jdbcTemplate.update("DELETE FROM object WHERE id = (?)", id);
    }

    @Override
    public void updateObject(int id, String name, String bucket) {
        jdbcTemplate.update("UPDATE object SET nom = (?), bucket = (?) WHERE id = (?)",
                name, bucket, id);
    }

    @Override
    public List<Object> getObjects(int bucket) {
        return jdbcTemplate.query("SELECT * FROM object WHERE bucket = (?)",
                bucketRowMapper, bucket);
    }
}
