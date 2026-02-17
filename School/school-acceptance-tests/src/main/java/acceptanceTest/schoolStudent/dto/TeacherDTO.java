package acceptanceTest.schoolStudent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
