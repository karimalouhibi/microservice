package org.example.forummicroserv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties({"etudiant", "enseignant"})  // Ignore les relations lors de la sérialisation

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // Le contenu du post

    private String imageUrl; // L'URL de l'image (si applicable)
    private String link; // Lien externe (si applicable)

    @Column(nullable = false)
    private LocalDateTime timestamp; // Date et heure de publication


    @Column(nullable = false)
    private boolean isApproved; // Statut de validation (si approuvé par l'admin)

    // Constructeur par défaut
    public Post() {}

    // Constructeur avec paramètres
    public Post(String content, String imageUrl, String link, LocalDateTime timestamp, boolean isApproved) {
        this.id=id;
        this.content = content;
        this.imageUrl = imageUrl;
        this.link = link;
        this.timestamp = timestamp;
        this.isApproved = isApproved;
    }
        // Getters
        public Long getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getLink() {
            return link;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public boolean isApproved() {
            return isApproved;
        }

        // Setters
        public void setId(long id) {
            this.id = id;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public void setApproved(boolean approved) {
            isApproved = approved;
        }
    }


