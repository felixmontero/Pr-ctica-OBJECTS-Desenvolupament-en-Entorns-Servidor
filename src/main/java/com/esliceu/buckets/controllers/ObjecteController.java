package com.esliceu.buckets.controllers;

import com.esliceu.buckets.models.File;
import com.esliceu.buckets.models.Objecte;
import com.esliceu.buckets.models.User;
import com.esliceu.buckets.services.ObjecteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.lang.constant.Constable;
import java.util.List;
@Controller
public class ObjecteController {
    @Autowired
    ObjecteService objecteService;

    @GetMapping("/objects/{bucket}")
    public String getObjects(@PathVariable String bucket, Model model){

        List<Objecte> objects = objecteService.getObjects(bucket);
        model.addAttribute("objects", objects);
        model.addAttribute("bucket", bucket);
        return "objects";
    }
    @PostMapping("/objects/{bucket}")
    public String createObject(@PathVariable String bucket, @RequestParam String path, @RequestParam MultipartFile file,
                               HttpServletResponse response, HttpSession session) throws IOException {
        String nickname = (String) session.getAttribute("nickname");
        try {
           objecteService.createObject(bucket, path, file, nickname);
         //byte[] uploadfile =file.getBytes();
        } catch (Exception e) {
        e.printStackTrace();
        }
        return "redirect:/objects/"+bucket;
}


    @GetMapping("/objects/{bucket}/**")
    public String getObjects(@RequestParam String bucket, HttpServletRequest req){
        String path = (String) req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        return "objects";
    }

    // create a method to download a file
    @GetMapping("/download/{objid}/{fid}")
    public ResponseEntity<byte[]> download(HttpServletRequest request, @PathVariable int objId, @PathVariable int FileId) {
        Objecte obj = objecteService.getObject(objId);
        File file = objecteService.getFile(FileId);

        byte[] content = file.getContent();
        String name = obj.getName();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/octet-stream"));
        headers.setContentLength(content.length);
        headers.set("Content-Disposition", "attachment; filename=" + name);
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    @PostMapping("/deleteObject/{id}")
    public String deleteObject(@PathVariable int id){
        objecteService.deleteObject(id);
        return "redirect:/objects";
    }
}
