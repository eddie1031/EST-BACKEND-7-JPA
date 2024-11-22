package domain.eg2._3;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(
        name = "CUSTOM_SEQUENCE_GENERATOR",
        sequenceName = "ACCOUNT_SEQ",
        initialValue = 1, allocationSize = 1
)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
    , generator = "CUSTOM_SEQUENCE_GENERATOR")
    private Long id;

    private String name;

}
