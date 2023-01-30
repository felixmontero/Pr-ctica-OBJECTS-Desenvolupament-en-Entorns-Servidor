package com.esliceu.buckets.controllers;

import com.esliceu.buckets.services.BucketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BucketController {

    @Autowired
    BucketService bucketService;
    @GetMapping("/objects")
    public String getObjects(HttpSession session, Model model){
       String nickname =(String) session.getAttribute("nickname");
        model.addAttribute("objects", bucketService.getBuckets(nickname));
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
