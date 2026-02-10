package SchoolStudent.restAPI.controllers.schoolClass;

import SchoolStudent.request.schoolClass.CreateSchoolClassRequest;
import SchoolStudent.response.schoolClass.CreateSchoolClassResponse;
import SchoolStudent.services.schoolClass.CreateSchoolClassService;
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
public class CreateSchoolClassRestController {

    private final CreateSchoolClassService service;

    @PostMapping(path = "/createSchoolClass",
            consumes = "application/json",
            produces = "application/json")
    public CreateSchoolClassResponse execute(@RequestBody CreateSchoolClassRequest request) {
        return service.execute(request);
    }
}
