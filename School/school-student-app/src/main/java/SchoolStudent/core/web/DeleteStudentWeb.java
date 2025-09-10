package SchoolStudent.core.web;

import SchoolStudent.core.request.student.DeleteStudentFromDatabaseRequest;
import SchoolStudent.core.response.student.DeleteStudentFromDatabaseResponse;
import SchoolStudent.core.service.DeleteStudentFromDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class DeleteStudentWeb {

    @Autowired private DeleteStudentFromDatabaseService service;

    @GetMapping("delete-student")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("response", new DeleteStudentFromDatabaseResponse());
        return "delete-student";
    }

    @PostMapping("delete-student")
    public String processForm(@RequestParam(value = "passwords") String passwords,
                              ModelMap modelMap) {
        List<String> stringList = new ArrayList<>(Arrays.asList(passwords.split(",")));
        for (int i = 0; i < stringList.size(); i++) {
            stringList.set(i, stringList.get(i).trim());
        }
        DeleteStudentFromDatabaseRequest request = new DeleteStudentFromDatabaseRequest(stringList);
        DeleteStudentFromDatabaseResponse response = service.execute(request);
        modelMap.addAttribute("response", response);
        return "delete-student";
    }
}
