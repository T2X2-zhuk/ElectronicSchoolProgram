package acceptanceTests.restController.schoolStudent.student;

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
import restControllers.schoolStudent.student.RegistrationStudentRestController;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class RegistrationStudentRestControllerTest {

//    @BeforeEach
    public void cleanDB(){
        CleanSchoolStudentsDbRestController.execute(true,true,true,false);
        CleanSchoolLessonsAndCertificatesDbRestController.execute(true,true,false);
    }

//    @Test
    public void successfulAcceptanceTest(){
        SchoolClassDTO schoolClassDTO = SchoolClassDTO.builder()
                .number(2L)
                .category("B").build();
        SpecificCodeForRegistrationInElectronicSchoolDTO code = SpecificCodeForRegistrationInElectronicSchoolDTO.builder()
                .specificCodeForRegistrationForStudent("StudentPassword33324").build();
        SchoolStudentDTO schoolStudentDTO = SchoolStudentDTO.builder()
                .firstName("Lara")
                .lastName("Devida")
                .fatherland("Namava")
                .email("gsffffd@gmail.com")
                .password("FlopaDno190")
                .schoolClassDTO(schoolClassDTO)
                .build();
        LessonDTO lessonDTO = LessonDTO.builder().name("Estonian language").build();
        CreateSchoolClassRestController.execute(schoolClassDTO).then().statusCode(200)
                .body("message",equalTo("School class successful created."));
        CreateClassLessonsRestController.execute(List.of(schoolClassDTO),lessonDTO).then().statusCode(200)
                .body("message",equalTo("Class lessons successfully created."))
        ;
        RegistrationStudentRestController.execute(schoolStudentDTO,code).then().statusCode(200)
                .body("message",equalTo("Student successfully registered. Certificates for this student successfully created."));

    }


}
