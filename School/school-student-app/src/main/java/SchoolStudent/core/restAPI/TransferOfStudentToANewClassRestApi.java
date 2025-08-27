package SchoolStudent.core.restAPI;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.response.TransferOfStudentToANewClassResponse;
import SchoolStudent.core.service.TransferOfStudentToANewClassService;
import com.google.common.base.Stopwatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/student/api")
public class TransferOfStudentToANewClassRestApi {

    @Autowired
    private TransferOfStudentToANewClassService service;
    @Autowired private RestRequestExecutionTimeLogger restRequestExecutionTimeLogger;

    @PostMapping(path = "/updateStudent",
            consumes = "application/json",
            produces = "application/json")
    public TransferOfStudentToANewClassResponse execute(@RequestBody TransferOfStudentToANewClassRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TransferOfStudentToANewClassResponse response = service.execute(request);
        restRequestExecutionTimeLogger.logExecutionTime(stopwatch);
        return response;
    }
}
