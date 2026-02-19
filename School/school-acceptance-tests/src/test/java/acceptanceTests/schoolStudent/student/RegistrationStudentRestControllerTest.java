package acceptanceTests.schoolStudent.student;

import acceptanceTest.schoolLessonsAndCertificates.dto.LessonDTO;
import acceptanceTest.schoolStudent.dto.SchoolClassDTO;
import acceptanceTest.schoolStudent.dto.SchoolStudentDTO;
import acceptanceTest.schoolStudent.dto.SpecificCodeForRegistrationInElectronicSchoolDTO;
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
        SchoolClassDTO schoolClassDTO = getSchoolClassDTO(2L,"B");
        SpecificCodeForRegistrationInElectronicSchoolDTO code = getSpecificCode(
                "","StudentPassword33324");
        SchoolStudentDTO schoolStudentDTO = getSchoolStudentDTO("Lara","Devida","Namava",
                "gsffffd@gmail.com","FlopaDno190",schoolClassDTO);
        LessonDTO lessonDTO = LessonDTO.builder().name("Estonian language").build();
        CreateSchoolClassRestController.execute(schoolClassDTO).then().statusCode(200)
                .body("message",equalTo("School class successful created."));
        CreateClassLessonsRestController.execute(List.of(schoolClassDTO),lessonDTO).then().statusCode(200)
                .body("message",equalTo("Class lessons successfully created."));
        RegistrationStudentRestController.execute(schoolStudentDTO,code).then().statusCode(200)
                .body("message",equalTo("Student successfully registered. Certificates for this student successfully created."));

    }

//    @Test
    public void acceptanceTest1(){
        SchoolClassDTO schoolClassDTO = getSchoolClassDTO(null,null);
        SpecificCodeForRegistrationInElectronicSchoolDTO code = getSpecificCode("","");
        SchoolStudentDTO schoolStudentDTO = getSchoolStudentDTO(
                "",
                "",
                "",
                "",
                "",
                schoolClassDTO);
        RegistrationStudentRestController.execute(schoolStudentDTO,code).then().statusCode(200)
                .body("errors[0].errorCode" ,
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_1"))
                .body("errors[0].description" ,
                        equalTo("Field first name must not be empty!"))
                .body("errors[1].errorCode",
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_2"))
                .body("errors[1].description",equalTo("Field last name must not be empty!"))
                .body("errors[2].errorCode" ,
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_3"))
                .body("errors[2].description" ,
                        equalTo("Field fatherland must not be empty!"))
                .body("errors[3].errorCode",
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_5"))
                .body("errors[3].description",equalTo("Field email must not be empty!"))
                .body("errors[4].errorCode" ,
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_8"))
                .body("errors[4].description" ,
                        equalTo("Field password must not be empty!"))
                .body("errors[5].errorCode",
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_15"))
                .body("errors[5].description",
                        equalTo("Field specific code for registration must not be empty!"))
                .body("errors[6].errorCode" ,
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_10"))
                .body("errors[6].description" ,
                        equalTo("Field number class must not be empty!"))
                .body("errors[7].errorCode",
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_12"))
                .body("errors[7].description",
                        equalTo("Field category class must not be empty!"));

    }

//    @Test
    public void acceptanceTest2(){
        SchoolClassDTO schoolClassDTO = getSchoolClassDTO(2L,"k");
        SpecificCodeForRegistrationInElectronicSchoolDTO code = getSpecificCode("", "Djsttgejc");
        SchoolStudentDTO schoolStudentDTO = getSchoolStudentDTO("Shima","Enaga","Kaca","HAGDASFDTSFDSSFS","aysdytsfdtrrew",schoolClassDTO);
        RegistrationStudentRestController.execute(schoolStudentDTO,code).then().statusCode(200)
                .body("errors[0].errorCode" ,
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_7"))
                .body("errors[0].description" ,
                        equalTo("Field email your email is not correct,example of correct mail - john.smith@gmail.com!"));
    }


//    @Test
    public void acceptanceTest3(){
        SchoolClassDTO schoolClassDTO = getSchoolClassDTO(2L,"C");
        SpecificCodeForRegistrationInElectronicSchoolDTO code = getSpecificCode("", "Djsttgejc");
        SchoolStudentDTO schoolStudentDTO = getSchoolStudentDTO("Shima","Enaga","Kaca","gsffffd@gmail.com","fdtrrew",schoolClassDTO);
        RegistrationStudentRestController.execute(schoolStudentDTO,code).then().statusCode(200)
                .body("errors[0].errorCode" ,
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_19"))
                .body("errors[0].description" ,
                        equalTo("You have entered the wrong specific code for registration!"));

    }

//    @Test
    public void acceptanceTest4(){
        SchoolClassDTO schoolClassDTO = getSchoolClassDTO(2L,"cl");
        SpecificCodeForRegistrationInElectronicSchoolDTO code = getSpecificCode("", "StudentPassword33324");
        SchoolStudentDTO schoolStudentDTO = getSchoolStudentDTO("Shima","Enaga","Kaca","gsffffd@gmail.com","fdtrrew",schoolClassDTO);
        RegistrationStudentRestController.execute(schoolStudentDTO,code).then().statusCode(200)
                .body("errors[0].errorCode" ,
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_13"))
                .body("errors[0].description" ,
                        equalTo("Field category class must contain one capital English letter!"));

    }

//    @Test
    public void acceptanceTest5(){
        SchoolClassDTO schoolClassDTO = getSchoolClassDTO(2L,"C");
        SpecificCodeForRegistrationInElectronicSchoolDTO code = getSpecificCode("", "StudentPassword33324");
        SchoolStudentDTO schoolStudentDTO = getSchoolStudentDTO("Lara","Devida","Namava","gsffffd@gmail.com","FlopaDno190",schoolClassDTO);
        LessonDTO lessonDTO = LessonDTO.builder().name("Estonian language").build();
        CreateSchoolClassRestController.execute(schoolClassDTO).then().statusCode(200)
                .body("message",equalTo("School class successful created."));
        CreateClassLessonsRestController.execute(List.of(schoolClassDTO),lessonDTO).then().statusCode(200)
                .body("message",equalTo("Class lessons successfully created."))
        ;
        RegistrationStudentRestController.execute(schoolStudentDTO,code).then().statusCode(200)
                .body("message",equalTo("Student successfully registered. Certificates for this student successfully created."));
        SchoolStudentDTO schoolStudentDTO2 = getSchoolStudentDTO("Lara","Devida","Namava","gsffffd@gmail.com","FlopaDno190",schoolClassDTO);
        RegistrationStudentRestController.execute(schoolStudentDTO2,code).then().statusCode(200)
                .body("errors[0].errorCode" ,
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_6"))
                .body("errors[0].description" ,
                        equalTo("Such email already exists!"));
    }

//    @Test
    public void acceptanceTest6(){
        SchoolClassDTO schoolClassDTO =
                getSchoolClassDTO(2L,"C");
        SpecificCodeForRegistrationInElectronicSchoolDTO code =
                getSpecificCode("", "StudentPassword33324");
        SchoolStudentDTO schoolStudentDTO =
                getSchoolStudentDTO(
                        "Lara","Devida","Namava",
                        "kalamara@gmail.com",
                        "FlopaDno190",
                        schoolClassDTO);
        LessonDTO lessonDTO =
                LessonDTO.builder().name("Estonian language").build();
        CreateSchoolClassRestController.execute(schoolClassDTO)
                .then().statusCode(200)
                .body("message",
                        equalTo("School class successful created."));
        CreateClassLessonsRestController
                .execute(List.of(schoolClassDTO),lessonDTO)
                .then().statusCode(200)
                .body("message",
                        equalTo("Class lessons successfully created."));
        RegistrationStudentRestController
                .execute(schoolStudentDTO,code)
                .then().statusCode(200)
                .body("message",
                        equalTo("Student successfully registered. Certificates for this student successfully created."));
        SchoolStudentDTO schoolStudentDTO2 =
                getSchoolStudentDTO(
                        "Shima","Enaga","Kaca",
                        "gsffffd@gmail.com",
                        "FlopaDno190",
                        schoolClassDTO);
        RegistrationStudentRestController
                .execute(schoolStudentDTO2,code)
                .then().statusCode(200)
                .body("errors[0].errorCode",
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_9"))
                .body("errors[0].description",
                        equalTo("Such password already exists!"));
    }

//    @Test
    public void acceptanceTest7(){
        SchoolClassDTO schoolClassDTO =
                getSchoolClassDTO(2L,"C");
        SpecificCodeForRegistrationInElectronicSchoolDTO code =
                getSpecificCode("", "StudentPassword33324");
        SchoolStudentDTO schoolStudentDTO =
                getSchoolStudentDTO(
                        "Lara","Devida","Namava",
                        "kalamara@gmail.com",
                        "FlopaDno190",
                        schoolClassDTO);
        LessonDTO lessonDTO =
                LessonDTO.builder().name("Estonian language").build();
        CreateSchoolClassRestController.execute(schoolClassDTO)
                .then().statusCode(200)
                .body("message",
                        equalTo("School class successful created."));
        CreateClassLessonsRestController
                .execute(List.of(schoolClassDTO),lessonDTO)
                .then().statusCode(200)
                .body("message",
                        equalTo("Class lessons successfully created."));
        RegistrationStudentRestController
                .execute(schoolStudentDTO,code)
                .then().statusCode(200)
                .body("message",
                        equalTo("Student successfully registered. Certificates for this student successfully created."));
        SchoolStudentDTO schoolStudentDTO2 =
                getSchoolStudentDTO(
                        "Shima","Enaga","Kaca",
                        "gsffffd@gmail.com",
                        "fdslksyyew",
                        getSchoolClassDTO(6L,"F"));
        RegistrationStudentRestController
                .execute(schoolStudentDTO2,code)
                .then().statusCode(200)
                .body("errors[0].errorCode",
                        equalTo("SCHOOL_STUDENT_ERROR_CODE_22"))
                .body("errors[0].description",
                        equalTo("Such school class is not exist!"));
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
