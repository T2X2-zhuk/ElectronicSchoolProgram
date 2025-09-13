package SchoolStudent.core.restAPI;

import SchoolStudent.core.request.student.DeleteStudentFromDatabaseRequest;
import SchoolStudent.core.response.student.DeleteStudentFromDatabaseResponse;
import SchoolStudent.core.service.interfeicesForServices.DeleteStudentFromDatabaseService;
import com.google.common.base.Stopwatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/student/api")
public class DeleteStudentRestController {

    @Autowired private DeleteStudentFromDatabaseService service;
    @Autowired private RestRequestExecutionTimeLogger restRequestExecutionTimeLogger;
    @PostMapping(path = "/deleteStudent",
            consumes = "application/json",
            produces = "application/json")
    public DeleteStudentFromDatabaseResponse execute(@RequestBody DeleteStudentFromDatabaseRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        DeleteStudentFromDatabaseResponse response = service.execute(request);
        restRequestExecutionTimeLogger.logExecutionTime(stopwatch);
        return response;
    }
}
