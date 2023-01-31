package com.esliceu.buckets.controllers;

import com.esliceu.buckets.models.Bucket;
import com.esliceu.buckets.services.BucketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BucketController {

    @Autowired
    BucketService bucketService;
    @GetMapping("/objects")
    public String getObjects(HttpSession session, Model model){
       String nickname =(String) session.getAttribute("nickname");
       List<Bucket> buckets = bucketService.getBuckets(nickname);
        System.out.println(buckets.size());
        model.addAttribute("buckets", buckets);
        return "objects";
    }

    @PostMapping("/objects")
    public String createBucket(@RequestParam String name, HttpSession session){
        String nickname =(String) session.getAttribute("nickname");
        bucketService.createBucket(name, nickname);
        return "redirect:/objects";
    }

    @GetMapping("/objects/{bucket}")
    public String getObjects(@RequestParam int bucket, Model model){
        List<Object> objects = bucketService.getObjects(bucket);
        model.addAttribute("objects", objects);
        return "objects";
    }

/*@GetMapping("/objects/{bucket}/**")
    public getObjects(@RequestParam String bucket, HttpServletRequest req){
        String path = req.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE;
        );

        return "objects";
    }
    */

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
