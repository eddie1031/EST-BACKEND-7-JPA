package domain.eg1;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    private String id;

    @Setter
    private String name;

    @Builder
    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
