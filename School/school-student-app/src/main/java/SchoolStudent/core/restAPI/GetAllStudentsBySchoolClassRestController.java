package SchoolStudent.core.restAPI;

import SchoolStudent.core.request.student.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.response.student.GetAllStudentsBySchoolClassResponse;
import SchoolStudent.core.service.interfeicesForServices.GetAllStudentsBySchoolClassService;
import com.google.common.base.Stopwatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/student/api")
public class GetAllStudentsBySchoolClassRestController {

    @Autowired private GetAllStudentsBySchoolClassService service;
    @Autowired private RestRequestExecutionTimeLogger restRequestExecutionTimeLogger;
    @PostMapping(path = "/getAllSchoolStudentBySchoolClass",
            consumes = "application/json",
            produces = "application/json")
    public GetAllStudentsBySchoolClassResponse execute(@RequestBody GetAllStudentsBySchoolClassRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        GetAllStudentsBySchoolClassResponse response = service.execute(request);
        restRequestExecutionTimeLogger.logExecutionTime(stopwatch);
        return response;
    }
}
