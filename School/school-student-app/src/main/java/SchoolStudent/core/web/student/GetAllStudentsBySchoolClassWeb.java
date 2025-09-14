package SchoolStudent.core.web.student;

import SchoolStudent.core.request.student.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.response.student.GetAllStudentsBySchoolClassResponse;
import SchoolStudent.core.service.interfeicesForServices.GetAllStudentsBySchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GetAllStudentsBySchoolClassWeb {

    @Autowired
    private GetAllStudentsBySchoolClassService service;

    @GetMapping("/get-all-students-by-school-class")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new GetAllStudentsBySchoolClassRequest());
        modelMap.addAttribute("response", new GetAllStudentsBySchoolClassResponse());
        return "get-all-students-by-school-class";
    }

    @PostMapping("/get-all-students-by-school-class")
    public String processForm(@ModelAttribute(value = "request") GetAllStudentsBySchoolClassRequest request,
                              ModelMap modelMap) {
        GetAllStudentsBySchoolClassResponse response = service.execute(request);
        modelMap.addAttribute("response", response);
        return "get-all-students-by-school-class";
    }
}
