package com.esliceu.buckets.controllers;

import com.esliceu.buckets.models.Bucket;
import com.esliceu.buckets.models.Objecte;
import com.esliceu.buckets.services.BucketService;
import com.esliceu.buckets.services.ObjecteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("buckets", buckets);
        return "buckets";
    }

    @PostMapping("/objects")
    public String createBucket(@RequestParam String name, HttpSession session){
        String nickname =(String) session.getAttribute("nickname");
        bucketService.createBucket(name, nickname);
        return "redirect:/objects";
    }
    @PostMapping("/deleteBucket/{nom}")
    public String deleteBucket(@PathVariable String nom){
        bucketService.deleteBucketByNom(nom);
        return "redirect:/objects";
    }


}
