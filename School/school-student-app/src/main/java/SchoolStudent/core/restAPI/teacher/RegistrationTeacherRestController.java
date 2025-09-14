package SchoolStudent.core.restAPI.teacher;

import SchoolStudent.core.request.student.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.core.response.student.GetAllStudentsBySchoolClassResponse;
import SchoolStudent.core.response.teacher.RegistrationTeacherResponse;
import SchoolStudent.core.service.interfeicesForServices.RegistrationTeacherService;
import com.google.common.base.Stopwatch;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/student/api")
public class RegistrationTeacherRestController {

    @Autowired private RegistrationTeacherService service;
    @PostMapping(path = "/registrationTeacher",
            consumes = "application/json",
            produces = "application/json")
    public RegistrationTeacherResponse execute(@RequestBody RegistrationTeacherRequest request) {
        return service.execute(request);
    }
}
