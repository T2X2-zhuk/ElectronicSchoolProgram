package SchoolLessonsAndCertificates.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CleanSchoolLessonsAndCertificatesDbRequest {

    private boolean cleanCertificateDb;
    private boolean cleanClassLessonDb;
    private boolean cleanLessonDb;

}
