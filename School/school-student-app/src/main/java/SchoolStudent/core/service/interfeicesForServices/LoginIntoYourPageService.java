package SchoolStudent.core.service.interfeicesForServices;

import SchoolStudent.core.request.student.LoginToYourStudentPageRequest;
import SchoolStudent.core.response.student.LoginToYourPageForStudentResponse;

public interface LoginIntoYourPageService {

    LoginToYourPageForStudentResponse execute(LoginToYourStudentPageRequest request);
}
