package SchoolStudent.core.web.student;

import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.response.student.RegistrationStudentInDatabaseResponse;

import SchoolStudent.core.service.interfeicesForServices.RegistrationStudentInDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterStudentInDatabaseWebController {

    @Autowired private RegistrationStudentInDatabaseService service;

    @GetMapping("register-student")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new RegistrationStudentInDatabaseRequest());
        modelMap.addAttribute("response", new RegistrationStudentInDatabaseResponse());
        return "register-student";
    }

    @PostMapping("register-student")
    public String processForm(@ModelAttribute(value = "request") RegistrationStudentInDatabaseRequest request,
                              ModelMap modelMap) {
        RegistrationStudentInDatabaseResponse response = service.execute(request);
        modelMap.addAttribute("response", response);
        return "register-student";
    }

}
