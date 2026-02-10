package SchoolLessonsAndCertificates.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassLessonDTO {

    private Long id;
    private Long schoolClassId;
    private LessonDTO lesson;
}
