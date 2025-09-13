package SchoolStudent.core.service.interfeicesForServices;

import SchoolStudent.core.request.student.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.response.student.TransferOfStudentToANewClassResponse;

public interface TransferOfStudentToANewClassService {
    TransferOfStudentToANewClassResponse execute(TransferOfStudentToANewClassRequest request);
}
