package SchoolStudent.restAPI.controllers.teacher;

import SchoolStudent.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.response.teacher.RegistrationTeacherResponse;
import SchoolStudent.services.teacher.RegistrationTeacherService;
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
public class RegistrationTeacherRestController {

    private final RegistrationTeacherService service;

    @PostMapping(path = "/registrationTeacher",
            consumes = "application/json",
            produces = "application/json")
    public RegistrationTeacherResponse execute(@RequestBody RegistrationTeacherRequest request) {
        return service.execute(request);
    }
}
