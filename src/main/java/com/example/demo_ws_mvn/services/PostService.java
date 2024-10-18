package com.example.demo_ws_mvn.services;
import com.example.demo_ws_mvn.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo_ws_mvn.models.Post;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    // We use this instead of Autowired as this is now the recommended practice
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Retrieve all posts.
     * @return List of all posts
     */
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /**
     * Retrieve a post by its id.
     * @param id The id of the post
     * @return Optional containing the post if found, or empty if not found
     */
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    /**
     * Create a new post.
     * @param post The post to be created
     * @return The created post
     */
    @Transactional
    public Post createPost(Post post) {
        post.setDate(LocalDateTime.now());
        return postRepository.save(post);
    }

    /**
     * Update an existing post.
     * @param id The id of the post to update
     * @param postDetails The updated post details
     * @return Optional containing the updated post if found, or empty if not found
     */
    @Transactional
    public Optional<Post> updatePost(Long id, Post postDetails) {
        return postRepository.findById(id)
                .map(existingPost -> {
                    existingPost.setAuthor(postDetails.getAuthor());
                    existingPost.setTitle(postDetails.getTitle());
                    existingPost.setContent(postDetails.getContent());
                    // We don't update the date here to preserve the original creation date
                    return postRepository.save(existingPost);
                });
    }

    /**
     * Delete a post by its id.
     * @param id The id of the post to delete
     * @return true if the post was deleted, false if it was not found
     */
    @Transactional
    public boolean deletePost(Long id) {
        return postRepository.findById(id)
                .map(post -> {
                    postRepository.delete(post);
                    return true;
                })
                .orElse(false);
    }
}
