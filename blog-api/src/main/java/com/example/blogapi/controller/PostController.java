package com.example.blogapi.controller;

import com.example.blogapi.model.Post;
import com.example.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @PostMapping
    public Post create(@RequestBody Post post) { return postRepository.save(post); }

    @GetMapping
    public List<Post> getAll() { return postRepository.findAll(); }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { postRepository.deleteById(id); }
}
