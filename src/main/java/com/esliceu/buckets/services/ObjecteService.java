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

        boolean countant = false;
        //mirar si File existe
        if(checkFile(hash)){
            //si existe, no se crea
            objectDAO.incrementAccountant(hash);
            countant = true;

        }else{
            //si no existe, se crea
            File fileObject = new File();
            fileObject.setContent(content);
            fileObject.setHash(hash);
            fileObject.setAccountant(1);
            objectDAO.createFile(fileObject);

        }
        Bucket bucket2 =  bucketDAO.getBucketByName(bucket);
        String nameObject = path + name;
        //comprobar si existe el objeto
        if(checkObject(bucket2.getId(), nameObject)){
            //si existe creamos nueva versión
            int ObjectID = (int) objectDAO.getIdObject(bucketDAO.getBucketByName(bucket).getId(), nameObject);
            if(countant){
                objectDAO.decrementAccountantByHash(hash);
            }

            int FileID = (int) objectDAO.getIdFile(hash);
            if(checkVersionsAlreadyExists(ObjectID, FileID)){

            }else{
                int version = objectDAO.getVersions(ObjectID).size() + 1;
                objectDAO.createVersion(FileID, ObjectID, new Date(), version);
            }
        }else{
            //si no existe, se crea

            Objecte objecte = new Objecte();
            objecte.setBucket(bucket2.getId());
            int FileID = (int) objectDAO.getIdFile(hash);
            objecte.setName(nameObject);
            objecte.setUser(nickname);
            objectDAO.createObject(objecte);
            int ObjectID = (int) objectDAO.getIdObject(bucketDAO.getBucketByName(bucket).getId(), nameObject);
            //id del objeto de la base de datos
            int id = (int) objectDAO.getIdObject(bucket2.getId(), nameObject);
            //Añadir versiones
            int version = 1;
            objectDAO.createVersion(FileID, ObjectID, new Date(), version);
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

    public Objecte getObject(int objId) {
        return objectDAO.getObject(objId);
    }

    public File getFile(int fileId) {
        return objectDAO.getFile(fileId);
    }

    public boolean checkVersionsAlreadyExists(int ObjectID, int FileID){
      List<ObjecteVersions> listVersions = objectDAO.getVersions(ObjectID);

      ObjecteVersions LastVersion = listVersions.get(0);

      if(LastVersion.getIdFile() == FileID){
          return true;
      }
        return false;
    }

    public void deleteObject(int objid) {
        int idFile = objectDAO.FileIdByVersion(objid);
        File file = objectDAO.getFile(idFile);
        if(file.getAccountant() == 1) {
            objectDAO.deleteFile(idFile);
        }else{
            objectDAO.decrementAccountant(idFile);
            objectDAO.deleteObject(objid);
        }
    }

    public void deleteObjectByPath(String name, String bucket) {
        Bucket bucket1 =bucketDAO.getBucketByName(bucket);
        int idObj = objectDAO.getIdObjectByName(name, bucket1.getId());
        int idFile = objectDAO.FileIdByVersion(idObj);
        File file = objectDAO.getFile(idFile);
        if(file.getAccountant() == 1) {
            objectDAO.deleteFile(idFile);
            objectDAO.deleteObject(idObj);

        }else{
            objectDAO.decrementAccountant(idFile);
            objectDAO.deleteObject(idObj);
        }
    }
}
