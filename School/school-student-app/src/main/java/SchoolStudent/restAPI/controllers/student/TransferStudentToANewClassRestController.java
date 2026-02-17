package SchoolStudent.restAPI.controllers.student;

import SchoolStudent.request.student.TransferStudentToNewClassRequest;
import SchoolStudent.response.student.TransferStudentToNewClassResponse;
import SchoolStudent.services.schoolStudent.TransferStudentToNewClassService;
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
public class TransferStudentToANewClassRestController {

    private final TransferStudentToNewClassService service;

    @PostMapping(path = "/transferStudent",
            consumes = "application/json",
            produces = "application/json")
    public TransferStudentToNewClassResponse execute(@RequestBody TransferStudentToNewClassRequest request) {
        return service.execute(request);
    }
}
