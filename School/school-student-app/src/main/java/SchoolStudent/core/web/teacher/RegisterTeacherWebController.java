package SchoolStudent.core.web.teacher;

import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.core.response.student.RegistrationStudentInDatabaseResponse;
import SchoolStudent.core.response.teacher.RegistrationTeacherResponse;
import SchoolStudent.core.service.interfeicesForServices.RegistrationTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterTeacherWebController {


    @Autowired private RegistrationTeacherService service;

    @GetMapping("register-teacher")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new RegistrationTeacherRequest());
        modelMap.addAttribute("response", new RegistrationTeacherResponse());
        return "register-teacher";
    }

    @PostMapping("register-teacher")
    public String processForm(@ModelAttribute(value = "request") RegistrationTeacherRequest request,
                              ModelMap modelMap) {
        RegistrationTeacherResponse response = service.execute(request);
        modelMap.addAttribute("response", response);
        return "register-teacher";
    }

}
