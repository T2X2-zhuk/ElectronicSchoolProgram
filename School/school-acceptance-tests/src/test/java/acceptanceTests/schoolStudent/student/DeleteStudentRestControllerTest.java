package acceptanceTests.schoolStudent.student;

import acceptanceTest.schoolLessonsAndCertificates.dto.LessonDTO;
import acceptanceTest.schoolStudent.dto.SchoolClassDTO;
import acceptanceTest.schoolStudent.dto.SchoolStudentDTO;
import acceptanceTest.schoolStudent.dto.SpecificCodeForRegistrationInElectronicSchoolDTO;
import restControllers.schoolLessonAndCertificates.CleanSchoolLessonsAndCertificatesDbRestController;
import restControllers.schoolLessonAndCertificates.CreateClassLessonsRestController;
import restControllers.schoolStudent.CleanSchoolStudentsDbRestController;
import restControllers.schoolStudent.schoolClass.CreateSchoolClassRestController;
import restControllers.schoolStudent.student.DeleteStudentRestController;
import restControllers.schoolStudent.student.RegistrationStudentRestController;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class DeleteStudentRestControllerTest {

//    @BeforeEach
    public void cleanDB(){
        CleanSchoolStudentsDbRestController.execute(true,true,true,false);
        CleanSchoolLessonsAndCertificatesDbRestController.execute(true,true,false);
    }

//    @Test
    public void successfulAcceptanceTest(){
        registrationStudents();
        DeleteStudentRestController.execute(List.of("gsffffd@gmail.com","lara@gmail.com")).then().statusCode(200)
                .body("message" , equalTo("Students and their certificates successfully deleted."));
    }

//    @Test
    public void unsuccessfulAcceptanceTest1(){
        registrationStudents();
        DeleteStudentRestController.execute(List.of("gsfffdddfd@gmail.com","larassdd@gmail.com")).then().statusCode(200)
                .body("errors[0].errorCode" , equalTo("SCHOOL_STUDENT_ERROR_CODE_4"))
                .body("errors[0].description" , equalTo("You have not entered a single email that is in the database!"));
    }

    private static void registrationStudents() {
        SchoolClassDTO schoolClassDTO = getSchoolClassDTO(2L,"B");
        SpecificCodeForRegistrationInElectronicSchoolDTO code = getSpecificCode(
                "","StudentPassword33324");
        SchoolStudentDTO schoolStudentDTO = getSchoolStudentDTO("Lara","Devida","Namava",
                "gsffffd@gmail.com","FlopaDno190",schoolClassDTO);
        SchoolStudentDTO schoolStudentDTO2 = getSchoolStudentDTO("Lara","Kjahsh","Namava",
                "lara@gmail.com","snanwe",schoolClassDTO);
        LessonDTO lessonDTO = LessonDTO.builder().name("Estonian language").build();
        CreateSchoolClassRestController.execute(schoolClassDTO).then().statusCode(200)
                .body("message",equalTo("School class successful created."));
        CreateClassLessonsRestController.execute(List.of(schoolClassDTO),lessonDTO).then().statusCode(200)
                .body("message",equalTo("Class lessons successfully created."));
        RegistrationStudentRestController.execute(schoolStudentDTO,code).then().statusCode(200)
                .body("message",equalTo("Student successfully registered. Certificates for this student successfully created."));
        RegistrationStudentRestController.execute(schoolStudentDTO2,code).then().statusCode(200)
                .body("message",equalTo("Student successfully registered. Certificates for this student successfully created."));
    }


    private static SpecificCodeForRegistrationInElectronicSchoolDTO getSpecificCode(String specificCodeForRegistrationForTeacher, String specificCodeForRegistrationForStudent) {
        return SpecificCodeForRegistrationInElectronicSchoolDTO.builder()
                .specificCodeForRegistrationForTeacher(specificCodeForRegistrationForTeacher)
                .specificCodeForRegistrationForStudent(specificCodeForRegistrationForStudent).build();
    }

    private static SchoolClassDTO getSchoolClassDTO(Long number, String category) {
        return SchoolClassDTO.builder()
                .number(number)
                .category(category).build();
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
