package SchoolStudent.core.restAPI;

import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.response.student.RegistrationStudentInDatabaseResponse;
import SchoolStudent.core.service.interfeicesForServices.RegistrationStudentInDatabaseService;
import com.google.common.base.Stopwatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/student/api")
public class SaveSchoolStudentRestController {

    @Autowired private RegistrationStudentInDatabaseService service;
    @Autowired private RestRequestExecutionTimeLogger restRequestExecutionTimeLogger;
    @PostMapping(path = "/saveStudent",
            consumes = "application/json",
            produces = "application/json")
    public RegistrationStudentInDatabaseResponse execute(@RequestBody RegistrationStudentInDatabaseRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        RegistrationStudentInDatabaseResponse response = service.execute(request);
        restRequestExecutionTimeLogger.logExecutionTime(stopwatch);
        return response;
    }
}
