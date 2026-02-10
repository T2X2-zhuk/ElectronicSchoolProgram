package SchoolStudent.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String fatherland;
    private String email;
    private String password;
    private String subject;
    private SchoolClassDTO schoolClassDTO;
}
