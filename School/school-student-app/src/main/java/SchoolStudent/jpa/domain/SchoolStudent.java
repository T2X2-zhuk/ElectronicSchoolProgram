package SchoolStudent.jpa.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "school_students")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolStudent {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @Column(name = "last_name" , nullable = false)
    private String lastName;

    @Column(name = "fatherland" , nullable = false)
    private String fatherland;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "password" , nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "classes_id", nullable = false)
    private SchoolClass schoolClass;
}
