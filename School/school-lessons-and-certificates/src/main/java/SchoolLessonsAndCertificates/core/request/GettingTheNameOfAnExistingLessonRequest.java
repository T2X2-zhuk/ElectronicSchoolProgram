package SchoolLessonsAndCertificates.core.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GettingTheNameOfAnExistingLessonRequest {
    private String lessonName;
}
