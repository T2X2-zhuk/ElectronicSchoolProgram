package SchoolStudent.restAPI.controllers.student;

import SchoolStudent.request.student.RegistrationStudentRequest;
import SchoolStudent.response.student.RegistrationStudentResponse;
import SchoolStudent.services.schoolStudent.RegistrationStudentService;
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
public class RegistrationStudentRestController {

    private final RegistrationStudentService service;

    @PostMapping(path = "/saveStudent",
            consumes = "application/json",
            produces = "application/json")
    public RegistrationStudentResponse execute(@RequestBody RegistrationStudentRequest request) {
        RegistrationStudentResponse response = service.execute(request);
        return response;
    }
}
