package domain.eg3._2;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class LockerOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

}
