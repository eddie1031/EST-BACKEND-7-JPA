package domain.eg3._2;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private LockerOwner owner;

}
