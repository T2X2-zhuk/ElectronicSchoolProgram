package SchoolStudent.restAPI.controllers.student;

import SchoolStudent.request.student.LoginStudentRequest;
import SchoolStudent.response.student.LoginStudentResponse;
import SchoolStudent.services.schoolStudent.LoginStudentService;
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
public class LoginStudentRestController {

    private final LoginStudentService service;

    @PostMapping(path = "/loginStudent",
                consumes = "application/json",
                produces = "application/json")
    public LoginStudentResponse execute(@RequestBody LoginStudentRequest request) {
        return service.execute(request);
    }
}
