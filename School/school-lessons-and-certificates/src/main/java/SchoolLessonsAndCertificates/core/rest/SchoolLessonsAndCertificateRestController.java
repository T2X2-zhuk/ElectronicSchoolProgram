package SchoolLessonsAndCertificates.core.rest;

import SchoolLessonsAndCertificates.core.request.GettingTheNameOfAnExistingLessonRequest;
import SchoolLessonsAndCertificates.core.request.SaveStudentRequest;
import SchoolLessonsAndCertificates.core.response.GettingTheNameOfAnExistingLessonResponse;
import SchoolLessonsAndCertificates.core.response.SaveStudentResponse;
import SchoolLessonsAndCertificates.core.services.GetSubjectByNameService;
import SchoolLessonsAndCertificates.core.services.SaveStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lessonsAndCertificates")
public class SchoolLessonsAndCertificateRestController {


    @Autowired  private SaveStudentService service;
    @Autowired private GetSubjectByNameService subjectByNameService;

    @PostMapping(path = "/saveStudent",
            consumes = "application/json",
            produces = "application/json")
    public SaveStudentResponse execute(@RequestBody SaveStudentRequest request) {
        return service.execute(request);
    }

    @PostMapping(path = "/getSubjectByName",
            consumes = "application/json",
            produces = "application/json")
    public GettingTheNameOfAnExistingLessonResponse execute(@RequestBody GettingTheNameOfAnExistingLessonRequest request) {
        return subjectByNameService.execute(request);
    }

}
