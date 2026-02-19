package acceptanceTests.schoolStudent.student;

import acceptanceTest.schoolLessonsAndCertificates.dto.LessonDTO;
import acceptanceTest.schoolStudent.dto.SchoolClassDTO;
import acceptanceTest.schoolStudent.dto.SchoolStudentDTO;
import acceptanceTest.schoolStudent.dto.SpecificCodeForRegistrationInElectronicSchoolDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restControllers.schoolLessonAndCertificates.CleanSchoolLessonsAndCertificatesDbRestController;
import restControllers.schoolLessonAndCertificates.CreateClassLessonsRestController;
import restControllers.schoolStudent.CleanSchoolStudentsDbRestController;
import restControllers.schoolStudent.schoolClass.CreateSchoolClassRestController;
import restControllers.schoolStudent.student.GetAllStudentsBySchoolClassRestController;
import restControllers.schoolStudent.student.RegistrationStudentRestController;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class GetStudentsBySchoolClassRestControllerTest {

//    @BeforeEach
    public void cleanDB(){
        CleanSchoolStudentsDbRestController.execute(true,true,true,false);
        CleanSchoolLessonsAndCertificatesDbRestController.execute(true,true,false);
    }

//    @Test
    public void successfulAcceptanceTest(){
        SchoolClassDTO schoolClassDTO = getSchoolClassDTO(2L,"B");
        registrationStudent(schoolClassDTO);
        GetAllStudentsBySchoolClassRestController.execute(schoolClassDTO).then().statusCode(200);
    }

//    @Test
    public void successfulAcceptanceTest2(){
        SchoolClassDTO schoolClassDTO = getSchoolClassDTO(2L,"B");
        SchoolClassDTO schoolClassDTO2 = getSchoolClassDTO(3L,"B");
        registrationStudent(schoolClassDTO);
        CreateSchoolClassRestController.execute(schoolClassDTO2).then().statusCode(200);
        GetAllStudentsBySchoolClassRestController.execute(schoolClassDTO2).then().statusCode(200)
                .body("errors[0].errorCode",equalTo("SCHOOL_STUDENT_ERROR_CODE_23"))
                .body("errors[0].description",equalTo("There are no students in this class!"));
    }

    private static void registrationStudent(SchoolClassDTO schoolClassDTO) {
        SpecificCodeForRegistrationInElectronicSchoolDTO code = getSpecificCode(
                "","StudentPassword33324");
        SchoolStudentDTO schoolStudentDTO = getSchoolStudentDTO("Lara","Devida","Namava",
                "gsffffd@gmail.com","FlopaDno190",schoolClassDTO);
        SchoolStudentDTO schoolStudentDTO2 = getSchoolStudentDTO("Fadon","Nenama","Kvacaxa",
                "fadon@gmail.com","fadon2390",schoolClassDTO);
        LessonDTO lessonDTO = LessonDTO.builder().name("Estonian language").build();
        CreateSchoolClassRestController.execute(schoolClassDTO).then().statusCode(200);
        CreateClassLessonsRestController.execute(List.of(schoolClassDTO),lessonDTO).then().statusCode(200);
        RegistrationStudentRestController.execute(schoolStudentDTO,code).then().statusCode(200);
        RegistrationStudentRestController.execute(schoolStudentDTO2,code).then().statusCode(200);
    }


    private static SchoolClassDTO getSchoolClassDTO(Long number, String category) {
        return SchoolClassDTO.builder()
                .number(number)
                .category(category).build();
    }

    private static SpecificCodeForRegistrationInElectronicSchoolDTO getSpecificCode(String specificCodeForRegistrationForTeacher, String specificCodeForRegistrationForStudent) {
        return SpecificCodeForRegistrationInElectronicSchoolDTO.builder()
                .specificCodeForRegistrationForTeacher(specificCodeForRegistrationForTeacher)
                .specificCodeForRegistrationForStudent(specificCodeForRegistrationForStudent).build();
    }

    private static SchoolStudentDTO getSchoolStudentDTO(String firstName,String lastName,String fatherland,String email,String password, SchoolClassDTO schoolClassDTO) {
        return SchoolStudentDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .fatherland(fatherland)
                .email(email)
                .password(password)
                .schoolClassDTO(schoolClassDTO)
                .build();
    }
}
