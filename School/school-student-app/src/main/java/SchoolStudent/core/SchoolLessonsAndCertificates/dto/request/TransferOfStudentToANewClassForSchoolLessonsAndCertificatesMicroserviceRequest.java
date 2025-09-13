package SchoolStudent.core.SchoolLessonsAndCertificates.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferOfStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceRequest {

    private Long number;
    private String category;
    private String email;
}
