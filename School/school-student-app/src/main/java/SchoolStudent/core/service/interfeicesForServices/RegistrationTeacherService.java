package SchoolStudent.core.service.interfeicesForServices;

import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.core.response.student.RegistrationStudentInDatabaseResponse;
import SchoolStudent.core.response.teacher.RegistrationTeacherResponse;

public interface RegistrationTeacherService {

    RegistrationTeacherResponse execute(RegistrationTeacherRequest request);
}
