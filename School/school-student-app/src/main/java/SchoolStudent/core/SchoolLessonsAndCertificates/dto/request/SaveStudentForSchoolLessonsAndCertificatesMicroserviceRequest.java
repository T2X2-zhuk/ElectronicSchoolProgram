package SchoolStudent.core.SchoolLessonsAndCertificates.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveStudentForSchoolLessonsAndCertificatesMicroserviceRequest {

    private String firstName;
    private String lastName;
    private String fatherland;
    private String email;
    private Long numberClass;
    private String category;

}
