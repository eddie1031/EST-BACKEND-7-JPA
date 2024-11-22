package domain.eg2._3;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
public class GymMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
//    @Enumerated(EnumType.ORDINAL)
    @Enumerated(EnumType.STRING)
    private Level membershipLevel;


}
