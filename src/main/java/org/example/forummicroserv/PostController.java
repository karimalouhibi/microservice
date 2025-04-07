package org.example.forummicroserv;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // ➕ Ajouter un post
    @PostMapping("create")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        try {
            Post savedPost = postService.createPost(post);
            return ResponseEntity.ok(savedPost);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 📌 Récupérer tous les posts
    @GetMapping("/list")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

//    // 📌 Récupérer un post par ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
//        Post post = postService.getPostById(id);
//        return post != null ? ResponseEntity.ok(post) : ResponseEntity.notFound().build();
//    }
//
    // ✏️ Mettre à jour un post
    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(id, post); // Correction du nom de méthode (updatePost)
        return updatedPost != null ? ResponseEntity.ok(updatedPost) : ResponseEntity.notFound().build();
    }

    // 🗑️ Supprimer un post
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(Long.valueOf(id)); // Suppression de String.valueOf(id), car l'id est déjà de type Long
        return ResponseEntity.noContent().build();
    }
}
