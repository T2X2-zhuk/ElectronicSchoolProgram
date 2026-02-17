package SchoolLessonsAndCertificates.response;

import SchoolLessonsAndCertificates.util.CoreResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CleanSchoolLessonsAndCertificatesDbResponse extends CoreResponse {

    private boolean deleteCertificateDb;
    private boolean deleteClassLessonDb;
    private boolean deleteLessonDb;
}
