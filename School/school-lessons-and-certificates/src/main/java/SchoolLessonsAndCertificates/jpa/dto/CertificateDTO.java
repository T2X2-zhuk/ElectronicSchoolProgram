package SchoolLessonsAndCertificates.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDTO {

    private Long id;
    private Long studentId;
    private ClassLessonDTO classLessonDTO;
    private Long first_trimester;
    private Long two_trimester;
    private Long three_trimester;
    private Long annual_subject_assessment;
}
