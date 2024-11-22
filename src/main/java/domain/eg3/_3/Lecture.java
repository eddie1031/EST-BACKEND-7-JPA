package domain.eg3._3;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
@Getter
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "STUDENT_LECTURE",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "LECTURE_ID")
    )
    private List<Student> studentList;

}
