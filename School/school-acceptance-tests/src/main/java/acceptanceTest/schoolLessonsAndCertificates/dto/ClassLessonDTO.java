package acceptanceTest.schoolLessonsAndCertificates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassLessonDTO {

    private Long id;
    private Long schoolClassId;
    private LessonDTO lesson;
}
