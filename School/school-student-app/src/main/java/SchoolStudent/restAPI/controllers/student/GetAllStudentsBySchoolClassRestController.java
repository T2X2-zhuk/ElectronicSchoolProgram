package SchoolStudent.restAPI.controllers.student;

import SchoolStudent.request.student.GetStudentsBySchoolClassRequest;
import SchoolStudent.response.student.GetStudentsBySchoolClassResponse;
import SchoolStudent.services.schoolStudent.GetStudentsBySchoolClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/student/api")
@RequiredArgsConstructor
@Slf4j
public class GetAllStudentsBySchoolClassRestController {

    private final GetStudentsBySchoolClassService service;

    @PostMapping(path = "/getAllSchoolStudentBySchoolClass",
            consumes = "application/json",
            produces = "application/json")
    public GetStudentsBySchoolClassResponse execute(@RequestBody GetStudentsBySchoolClassRequest request) {
        return service.execute(request);
    }
}
