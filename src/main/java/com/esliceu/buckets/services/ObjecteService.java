package com.esliceu.buckets.services;

import com.esliceu.buckets.daos.BucketDAO;
import com.esliceu.buckets.daos.ObjecteDAO;
import com.esliceu.buckets.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;



@Service
public class ObjecteService {
    @Autowired
    ObjecteDAO objectDAO;
    @Autowired
    BucketDAO bucketDAO;

    public List<Objecte> getObjects(String bucket) {
        Bucket bucket1= bucketDAO.getBucketByName(bucket);
        return objectDAO.getObjects(bucket1.getId());
    }

    public void createObject(String bucket, String path, MultipartFile file, String nickname) throws IOException {
        String name = file.getOriginalFilename();
        byte [] content = file.getBytes();
        String hash = encryptByBytes(content);

        //mirar si el ultimo caracter es un /
        if(path.charAt(path.length()-1) != '/'){
            path = path + "/";
        }
        //mirar si el primer caracter es un /
        if(path.charAt(0) == '/'){
            path = path.substring(1);
        }
        while (path.contains("//")){
            path = path.replace("//", "/");
        }


        //mirar si File existe
        if(checkFile(hash)){
            //si existe, no se crea

        }else{
            //si no existe, se crea
            File fileObject = new File();
            fileObject.setContent(content);
            fileObject.setHash(hash);
            fileObject.setAccountant(1);
            objectDAO.createFile(fileObject);

        }
        Bucket bucket2 =  bucketDAO.getBucketByName(bucket);
        System.out.println(bucket2.getId());
        //comprobar si existe el objeto
        if(checkObject(bucket2.getId(), name)){
            //si existe creamos nueva versión
            int ObjectID = objectDAO.getIdObject(bucketDAO.getBucketByName(bucket).getId(), name);

            return;
        }else{
            //si no existe, se crea

            Objecte objecte = new Objecte();
            objecte.setBucket(bucket2.getId());
            objecte.setName(name);
            objecte.setUser(nickname);
            objectDAO.createObject(objecte);
            //id del objeto de la base de datos
            int id = objectDAO.getIdObject(bucket2.getId(), name);
            //Añadir versiones
            int version = 1;
            objectDAO.createVersion(id, new Date(), objectDAO.getIdFile(hash), version);

        }
    }

    private boolean checkFile(String path) {
        return objectDAO.checkFile(path);

    }

    public String encryptByBytes(byte[] content){
        //Encrypt hash with SHA-256
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(content);
        StringBuffer sb = new StringBuffer();

        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    }
    //create object model
    public void createObjectModel(String bucket, String path, String name, String hash, User nickname){


    }
    public boolean checkObject(int bucket, String name){
        return objectDAO.checkObject(bucket, name);
    }
}
