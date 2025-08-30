package SchoolStudent.core.web;

import SchoolStudent.core.request.DeleteStudentFromDatabaseRequest;
import SchoolStudent.core.request.GetAllStudentsBySchoolClassRequest;
import SchoolStudent.core.request.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.response.DeleteStudentFromDatabaseResponse;
import SchoolStudent.core.response.GetAllStudentsBySchoolClassResponse;
import SchoolStudent.core.service.GetAllStudentsBySchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
