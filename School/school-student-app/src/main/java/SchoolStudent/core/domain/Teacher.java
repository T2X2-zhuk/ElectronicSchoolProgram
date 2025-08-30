package SchoolStudent.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name" , nullable = false)
    private String first_name;

    @Column(name = "last_name" , nullable = false)
    private String last_name;

    @Column(name = "fatherland" , nullable = false)
    private String fatherland;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "subject" , nullable = false)
    private String subject;

    @ManyToOne
    @JoinColumn(name = "classes_id", nullable = false)
    private SchoolClass classes_id;
}
