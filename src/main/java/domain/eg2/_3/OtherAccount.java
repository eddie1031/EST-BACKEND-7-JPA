package domain.eg2._3;

import jakarta.persistence.*;

@Entity
@Table(name = "other_acccount")
@TableGenerator(
        name = "ACCOUNT_TABLE_GENERATOR",
        table = "CST_SEQUENCE_CHECK",
        pkColumnName = "OHTER_ACCOUNT_SEQ", allocationSize = 1
)
public class OtherAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE
    , generator = "ACCOUNT_TABLE_GENERATOR")
    private Long id;

    private String name;

}
