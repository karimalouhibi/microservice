package org.example.forummicroserv;

import java.util.List;

public interface IPostService {
    Post createPost(Post post);
    List<Post> getAllPosts(); // Retourne une liste de posts
    Post getPostById(Long id); // Récupérer un post par ID
    Post updatePost(Long id, Post post); // Mettre à jour un post
    void deletePost(Long id); // Supprimer un post par ID
}
