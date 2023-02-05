package com.esliceu.buckets.controllers;

import com.esliceu.buckets.models.Objecte;
import com.esliceu.buckets.services.ObjecteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public String createObject(@PathVariable String bucket,@RequestParam String path, @RequestParam MultipartFile file,
                             HttpServletResponse response) throws IOException {
        System.out.println(bucket);
        try {
         byte[] uploadfile =file.getBytes();
        } catch (Exception e) {
        e.printStackTrace();
        }
        return "redirect:objects";
}


    @GetMapping("/objects/{bucket}/**")
    public String getObjects(@RequestParam String bucket, HttpServletRequest req){
        String path = (String) req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        return "objects";
    }


    // create a method to download a file
    /*@GetMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request) {
        byte[] content = file.getContent();
        String name = obj.getName();
        HttpHeaders headers = new HttpHeaders();
        headers.getContentType(MediaType.Value.of("application/octet-stream"));
        headers.setContentLength(content.length);
        headers.set("Content-Disposition", "attachment; filename=" + name);
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }*/
}