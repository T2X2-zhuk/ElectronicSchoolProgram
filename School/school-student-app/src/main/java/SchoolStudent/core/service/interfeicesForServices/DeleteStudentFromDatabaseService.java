package SchoolStudent.core.service.interfeicesForServices;

import SchoolStudent.core.request.student.DeleteStudentFromDatabaseRequest;
import SchoolStudent.core.response.student.DeleteStudentFromDatabaseResponse;

public interface DeleteStudentFromDatabaseService {

    DeleteStudentFromDatabaseResponse execute(DeleteStudentFromDatabaseRequest request);
}
