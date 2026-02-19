package acceptanceTests.lessonAndCertificates.certificate;
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

import static org.hamcrest.Matchers.equalTo;

public class CreateCertificatesRestControllerTest {

//    @BeforeEach
    public void cleanDB(){
        CleanSchoolStudentsDbRestController.execute(true,true,true,false);
        CleanSchoolLessonsAndCertificatesDbRestController.execute(true,true,false);
    }

//    @Test
    public void acceptanceTest(){
        SchoolClassDTO schoolClassDTO = getSchoolClassDTO(2L,"B");
        SpecificCodeForRegistrationInElectronicSchoolDTO code = getSpecificCode(
                "","StudentPassword33324");
        SchoolStudentDTO schoolStudentDTO = getSchoolStudentDTO("Lara","Devida","Namava",
                "gsffffd@gmail.com","FlopaDno190",schoolClassDTO);
        CreateSchoolClassRestController.execute(schoolClassDTO).then().statusCode(200)
                .body("message",equalTo("School class successful created."));
        RegistrationStudentRestController.execute(schoolStudentDTO,code).then().statusCode(200)
                .body("errors[0].errorCode",equalTo("LESSONS_AND_CERTIFICATES_ERROR_CODE_5"))
                .body("errors[0].description",equalTo("With a school class like this, any class lessons couldn't be found!"));

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
