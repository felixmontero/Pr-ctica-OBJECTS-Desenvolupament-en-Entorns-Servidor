package com.esliceu.buckets.daos;

import com.esliceu.buckets.models.File;
import com.esliceu.buckets.models.Objecte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public class ObjecteDAOImpl implements ObjecteDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Objecte> objectRowMapper = (rs, rn) -> {
        Objecte object = new Objecte();
        object.setId(rs.getInt("id"));
        object.setName(rs.getString("name"));
        object.setBucket(rs.getInt("bucketId"));
        return object;
    };

    @Override
    public void createObject(Objecte object) {
        jdbcTemplate.update("INSERT INTO objects (name, bucketId,user) VALUES (?,?,?)",
                object.getName(), object.getBucket(), object.getUser());
    }

    @Override
    public void deleteObject(int id) {
        jdbcTemplate.update("DELETE FROM objects WHERE id = (?)", id);
    }

    @Override
    public void updateObject(int id, String name, String bucket) {
        jdbcTemplate.update("UPDATE objects SET nom = (?), bucket = (?) WHERE id = (?)",
                name, bucket, id);
    }

    @Override
    public List<Objecte> getObjects(int bucket) {
        return jdbcTemplate.query("SELECT * FROM objects WHERE bucketId = (?)",
                new Object[]{bucket}, objectRowMapper);
    }

    @Override
    public boolean checkObject(int bucket, String name) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM objects WHERE bucketId = (?) AND name = (?)",
                new Object[]{bucket, name}, Integer.class) > 0;
    }

    @Override
    public Object getIdObject(int id, String nameObject) {
        //obtener id de un objeto
        try {
            return jdbcTemplate.queryForObject("SELECT id FROM objects WHERE bucketId = (?) AND name = (?)",
                    new Object[]{id, nameObject}, Integer.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void createFile(File file) {
        jdbcTemplate.update("INSERT INTO file (hash,content,accountant) VALUES (?,?,?)",
                 file.getHash(), file.getContent(),file.getAccountant());
    }

    @Override
    public boolean checkFile(String path) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM file WHERE hash = (?)",
                new Object[]{path}, Integer.class) > 0;
    }

    @Override
    public Object getIdFile(String hash) {
        return jdbcTemplate.queryForObject("SELECT id FROM file WHERE hash = (?)",
                new Object[]{hash}, Integer.class);
    }

    @Override
    public void createVersion(int FileID,int ObjectID, Date date,int version) {
        jdbcTemplate.update("INSERT INTO objectsFile (idFile,idObj,Date,version) VALUES (?,?,?,?)",
               FileID,ObjectID,date,version);
    }

    @Override
    public Objecte getObject(int objId) {
        return jdbcTemplate.queryForObject("SELECT * FROM objects WHERE id = (?)",
                new Object[]{objId}, objectRowMapper);
    }

    @Override
    public File getFile(int fileId) {
        return jdbcTemplate.queryForObject("SELECT * FROM file WHERE id = (?)",
                new Object[]{fileId}, (rs, rn) -> {
                    File file = new File();
                    file.setId(rs.getInt("id"));
                    file.setHash(rs.getString("hash"));
                    file.setContent(rs.getBytes("content"));
                    file.setAccountant(rs.getInt("accountant"));
                    return file;
                });
    }

    @Override
    public void incrementAccountant(String hash) {
        jdbcTemplate.update("UPDATE file SET accountant = accountant + 1 WHERE hash = (?)",
                hash);
    }

    @Override
    public int FileIdByVersion(int objid) {
        return jdbcTemplate.queryForObject("SELECT idFile FROM objectsFile WHERE idObj = (?)",
                new Object[]{objid}, Integer.class);
    }

    @Override
    public void decrementAccountant(int idFile) {
        jdbcTemplate.update("UPDATE file SET accountant = accountant - 1 WHERE id = (?)",
                idFile);
    }

    @Override
    public void deleteFile(int idFile) {
        jdbcTemplate.update("DELETE FROM file WHERE id = (?)", idFile);
    }
}
