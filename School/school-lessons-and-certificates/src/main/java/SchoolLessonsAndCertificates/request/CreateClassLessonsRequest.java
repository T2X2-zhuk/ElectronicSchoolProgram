package SchoolLessonsAndCertificates.request;

import SchoolLessonsAndCertificates.jpa.dto.LessonDTO;
import SchoolLessonsAndCertificates.jpa.dto.SchoolClassDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateClassLessonsRequest {

    private List<SchoolClassDTO> schoolClassDTOS;
    private LessonDTO lessonDTO;
    private List<Long> schoolClassIds;
}
