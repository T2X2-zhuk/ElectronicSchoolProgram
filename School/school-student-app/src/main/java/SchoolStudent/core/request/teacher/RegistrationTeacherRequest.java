package SchoolStudent.core.request.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationTeacherRequest {

    private String first_name;
    private String last_name;
    private String fatherland;
    private String email;
    private String password;
    private String subject;
    private Long number;
    private String category;
    private String specificCodeForRegistrationTeacher;
}
