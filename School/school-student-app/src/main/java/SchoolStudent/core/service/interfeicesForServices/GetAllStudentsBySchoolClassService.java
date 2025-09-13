package SchoolStudent.core.service.interfeicesForServices;

import SchoolStudent.core.request.student.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.response.student.GetAllStudentsBySchoolClassResponse;

public interface GetAllStudentsBySchoolClassService {

    GetAllStudentsBySchoolClassResponse execute(GetAllStudentsBySchoolClassRequest request);

}
