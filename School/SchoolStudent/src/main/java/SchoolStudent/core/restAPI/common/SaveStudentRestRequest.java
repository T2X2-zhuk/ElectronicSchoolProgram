package SchoolStudent.core.restAPI.common;

import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.response.RegistrationStudentInDatabaseResponse;
import SchoolStudent.core.service.RegistrationStudentInDatabaseService;
import com.google.common.base.Stopwatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/student/api")
public class SaveStudentRestRequest {

    @Autowired private RegistrationStudentInDatabaseService service;
    @Autowired private RestRequestExecutionTimeLogger restRequestExecutionTimeLogger;
    @PostMapping(path = "/saveStudent",
            consumes = "application/json",
            produces = "application/json")
    public RegistrationStudentInDatabaseResponse calculatePremium(@RequestBody RegistrationStudentInDatabaseRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        RegistrationStudentInDatabaseResponse response = service.execute(request);
        restRequestExecutionTimeLogger.logExecutionTime(stopwatch);
        return response;
    }
}
