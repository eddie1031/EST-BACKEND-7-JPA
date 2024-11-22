package domain.eg3._3;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "studentList")
    private List<Lecture> lectureList;

}
