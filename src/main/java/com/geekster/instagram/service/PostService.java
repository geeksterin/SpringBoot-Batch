package com.geekster.instagram.service;

import com.geekster.instagram.dao.PostRepository;
import com.geekster.instagram.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public int savePost(Post post) {
        Post savedPost = postRepository.save(post);
        return savedPost.getPostId();

    }

}
