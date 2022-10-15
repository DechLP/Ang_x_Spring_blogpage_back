package com.programming.dech.springngblog.service;

import com.programming.dech.springngblog.dto.PostDto;
import com.programming.dech.springngblog.exception.PostNotFoundException;
import com.programming.dech.springngblog.model.Post;
import com.programming.dech.springngblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

    @Autowired
    private AuthService authService;

    @Autowired
    private PostRepository postRepository;

    public List<PostDto> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        return postDto;
    }

    private Post mapFromPostToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User loggedInUser = authService.getCurrentUser().orElseThrow(() ->
                new IllegalArgumentException("User Not Found."));
        post.setCreatedOn(Instant.now());
        post.setUsername(loggedInUser.getUsername());
        post.setUpdateOn(Instant.now());
        return post;
    }

    public void createPost(PostDto postDto) {
        Post post = mapFromPostToPost(postDto);
        postRepository.save(post);
    }

    public PostDto readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new PostNotFoundException("For id " + id));
        return mapFromPostToDto(post);
    }
}
