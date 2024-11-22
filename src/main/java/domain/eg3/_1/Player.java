package domain.eg3._1;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "players_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
