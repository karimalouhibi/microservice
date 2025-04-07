package org.example.forummicroserv;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class PostService {
    private final PostRepository postRepository;


    // 🔥 Liste des mots interdits (modifiable selon ton besoin)
    private static final List<String> BAD_WORDS = Arrays.asList(
            "idiot", "stupide", "abruti", "imbécile", "con", "merde",
            "foutre", "salaud", "enculé", "putain", "bordel", "saleté","shit" // Remplace par de vrais mots interdits
    );

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 🚨 Fonction pour vérifier si un post contient un mot interdit
    private boolean containsBadWords(String content) {
        for (String word : BAD_WORDS) {
            if (Pattern.compile("(?i)\\b" + Pattern.quote(word) + "\\b").matcher(content).find()) {
                return true;
            }
        }
        return false;
    }

    // 🔍 Fonction pour filtrer les mots interdits
    private String filterBadWords(String content) {
        for (String word : BAD_WORDS) {
            String regex = "(?i)\\b" + Pattern.quote(word) + "\\b";
            content = content.replaceAll(regex, "****");
        }
        return content;
    }

    // ➕ Ajouter un post avec filtrage des gros mots
    public Post createPost(Post post) {
        if (containsBadWords(post.getContent())) {
            throw new IllegalArgumentException("Votre post contient des mots inappropriés !");
        }

        post.setContent(filterBadWords(post.getContent()));
        return postRepository.save(post);
    }

    // 📌 Récupérer tous les posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 📌 Récupérer un post par ID
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // ✏️ Mettre à jour un post avec filtrage des gros mots
    public Post updatePost(Long id, Post postDetails) {
        return postRepository.findById(id).map(post -> {
            if (containsBadWords(postDetails.getContent())) {
                throw new IllegalArgumentException("Votre post contient des mots inappropriés !");
            }

            post.setContent(filterBadWords(postDetails.getContent()));
            post.setImageUrl(postDetails.getImageUrl());
            post.setLink(postDetails.getLink());

            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post non trouvé avec l'ID : " + id));
    }

    // 🗑️ Supprimer un post
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post non trouvé avec l'ID : " + id);
        }
        postRepository.deleteById(id);
    }
}
