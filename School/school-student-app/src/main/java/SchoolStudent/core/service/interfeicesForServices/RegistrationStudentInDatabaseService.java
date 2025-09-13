package SchoolStudent.core.service.interfeicesForServices;

import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.response.student.RegistrationStudentInDatabaseResponse;

public interface RegistrationStudentInDatabaseService {

    RegistrationStudentInDatabaseResponse execute(RegistrationStudentInDatabaseRequest request);
}
