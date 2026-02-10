package SchoolStudent.web.student;

import SchoolStudent.request.student.TransferStudentToNewClassRequest;
import SchoolStudent.response.student.TransferStudentToNewClassResponse;
import SchoolStudent.services.schoolStudent.TransferStudentToNewClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TransferStudentToNewClassWebController {

    private final TransferStudentToNewClassService service;

    @GetMapping("transfer-student")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TransferStudentToNewClassRequest());
        modelMap.putIfAbsent("response", new TransferStudentToNewClassResponse());
        return "transfer-student";
    }

    @PostMapping("transfer-student")
    public String processForm(@ModelAttribute(value = "request") TransferStudentToNewClassRequest request,
                              RedirectAttributes redirectAttributes) {
        TransferStudentToNewClassResponse response = service.execute(request);
        redirectAttributes.addFlashAttribute("response", response);
        return "redirect:/transfer-student";
    }
}
