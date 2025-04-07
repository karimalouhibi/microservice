package org.example.forummicroserv;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PostDTO {
    private String content;
    private String imageUrl;
    private String link;
    private LocalDateTime timestamp;
    private boolean isApproved;
}
