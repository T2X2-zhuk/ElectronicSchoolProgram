package SchoolStudent.restAPI.controllers;

import SchoolStudent.request.CleanSchoolStudentDbRequest;
import SchoolStudent.response.CleanSchoolStudentDbResponse;
import SchoolStudent.services.CleanDBService;
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
public class CleanDBRestController {

    private final CleanDBService service;

    @PostMapping(path = "/cleanDb",
            consumes = "application/json",
            produces = "application/json")
    public CleanSchoolStudentDbResponse execute(@RequestBody CleanSchoolStudentDbRequest request) {
        return service.execute(request);
    }
}
