package domain.eg2._1;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @Column(unique = true)
    private Integer id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "contents", columnDefinition = "TEXT")
    private String contents;

    @Builder
    public Post(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

}
