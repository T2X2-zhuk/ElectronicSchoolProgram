package SchoolStudent.core.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationStudentInDatabaseRequest{

    private String firstName;
    private String lastName;
    private String fatherland;
    private String email;
    private String password;
    private Long number;
    private String category;
    private String specificCodeForRegistration;
}
