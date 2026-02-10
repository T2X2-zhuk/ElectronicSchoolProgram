package SchoolStudent.restAPI.controllers.student;

import SchoolStudent.request.student.DeleteStudentRequest;
import SchoolStudent.response.student.DeleteStudentResponse;
import SchoolStudent.services.schoolStudent.DeleteStudentService;
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
public class DeleteStudentRestController {

    private final DeleteStudentService service;

    @PostMapping(path = "/deleteStudent",
            consumes = "application/json",
            produces = "application/json")
    public DeleteStudentResponse execute(@RequestBody DeleteStudentRequest request) {
        return service.execute(request);
    }
}
