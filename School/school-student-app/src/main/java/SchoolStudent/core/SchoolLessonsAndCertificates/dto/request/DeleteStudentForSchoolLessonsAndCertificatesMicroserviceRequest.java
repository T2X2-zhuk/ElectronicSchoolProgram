package SchoolStudent.core.SchoolLessonsAndCertificates.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteStudentForSchoolLessonsAndCertificatesMicroserviceRequest {

    private List<String> emails;
}
