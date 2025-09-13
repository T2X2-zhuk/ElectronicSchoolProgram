package SchoolStudent.core.web;

import SchoolStudent.core.request.student.TransferOfStudentToANewClassRequest;
import SchoolStudent.core.response.student.TransferOfStudentToANewClassResponse;
import SchoolStudent.core.service.interfeicesForServices.TransferOfStudentToANewClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransferOfStudentToANewClassWeb {

    @Autowired private TransferOfStudentToANewClassService service;

    @GetMapping("transfer-student")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TransferOfStudentToANewClassRequest());
        modelMap.addAttribute("response", new TransferOfStudentToANewClassResponse());
        return "transfer-student";
    }

    @PostMapping("transfer-student")
    public String processForm(@ModelAttribute(value = "request") TransferOfStudentToANewClassRequest request,
                              ModelMap modelMap) {
        TransferOfStudentToANewClassResponse response = service.execute(request);
        modelMap.addAttribute("response", response);
        return "transfer-student";
    }
}
