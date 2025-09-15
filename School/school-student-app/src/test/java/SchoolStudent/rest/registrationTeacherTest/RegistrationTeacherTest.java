package SchoolStudent.rest.registrationTeacherTest;

import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.SaveStudentForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceResponse;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.SaveStudentForSchoolLessonsAndCertificatesMicroserviceResponse;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForDeleteStudents;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForGetSubjectNameService;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForSaveStudent;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForTransferStudent;
import SchoolStudent.core.dto.ValidationErrorDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class RegistrationTeacherTest extends RegistrationTeacherTestCase {

    private static final String TEST_FILE_BASE_FOLDER = "teacherFile/forRegistrationTeacher";
    @MockBean
    protected SchoolLessonsAndCertificatesMicroserviceForSaveStudent schoolLessonsAndCertificatesMicroserviceForSaveStudent;
    @MockBean
    protected SchoolLessonsAndCertificatesMicroserviceForTransferStudent transferStudent;
    @MockBean
    protected SchoolLessonsAndCertificatesMicroserviceForGetSubjectNameService getSubjectNameService;
    @MockBean
    protected SchoolLessonsAndCertificatesMicroserviceForDeleteStudents deleteStudents;


    @Test
    @DisplayName("check_Successful")
    public void check_Successful() throws Exception {
        mockSuccessfulRegistration();
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/check_Successful");
    }

    @Test
    @DisplayName("ERROR_CODE_1_first_name_must_not_be_null")
    public void check_ERROR_CODE_1() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_1_first_name_must_not_be_null");
    }

    @Test
    @DisplayName("ERROR_CODE_2_last_name_must_not_be_empty")
    public void check_ERROR_CODE_2() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_2_last_name_must_not_be_empty");
    }

    @Test
    @DisplayName("field_fatherland_must_not_be_empty")
    public void check_ERROR_CODE_3() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_3_field_fatherland_must_not_be_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_5_field_email_must_not_be_empty")
    public void check_ERROR_CODE_5() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_5_field_email_must_not_be_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_6_such_email_already_exists")
    public void check_ERROR_CODE_6() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_6_such_email_already_exists");
    }



    @Test
    @DisplayName("ERROR_CODE_7_email_your_email_is_not_correct")
    public void check_ERROR_CODE_7() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_7_email_your_email_is_not_correct");
    }

    @Test
    @DisplayName("ERROR_CODE_8_field_password_must_not_be_empty")
    public void check_ERROR_CODE_8() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_8_field_password_must_not_be_empty");
    }



    @Test
    @DisplayName("ERROR_CODE_9_such_password_already_exists")
    public void check_ERROR_CODE_9() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_9_such_password_already_exists");
    }

    @Test
    @DisplayName("ERROR_CODE_10_field_number_class_must_not_be_empty")
    public void check_ERROR_CODE_10() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_10_field_number_class_must_not_be_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_11_number_class_must_be_1-11")
    public void check_ERROR_CODE_11() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_11_number_class_must_be_1-11");
    }

    @Test
    @DisplayName("ERROR_CODE_12_field_category_class_must_not_be_empty")
    public void check_ERROR_CODE_12() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_12_field_category_class_must_not_be_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_13_field_category_class_must_contain_one_capital_english_letter")
    public void check_ERROR_CODE_13() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_13_field_category_class_must_contain_one_capital_english_letter");
    }

    @Test
    @DisplayName("ERROR_CODE_14_such_category_class_is_not_in_database")
    public void check_ERROR_CODE_14() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_14_such_category_class_is_not_in_database");
    }

    @Test
    @DisplayName("ERROR_CODE_15_field_specific_code_for_registration_must_not_be_empty")
    public void check_ERROR_CODE_15() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_15_field_specific_code_for_registration_must_not_be_empty");
    }



    @Test
    @DisplayName("ERROR_CODE_16_the_wrong_specific_code")
    public void check_ERROR_CODE_16() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_16_the_wrong_specific_code");
    }

    @Test
    @DisplayName("ERROR_CODE_17_such_lesson_is_not_database")
    public void check_ERROR_CODE_17() throws Exception {
        mockUnSuccessfulRegistration();
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_17_such_lesson_is_not_database");
    }

    @Test
    @DisplayName("ERROR_CODE_20_lesson_must_not_be_empty")
    public void check_ERROR_CODE_20() throws Exception {
        mockUnSuccessfulRegistration();
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_20_lesson_must_not_be_empty");
    }
    private void mockUnSuccessfulRegistration() {
        GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceResponse mockResponse =
                new GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceResponse();
        mockResponse.setErrors(
                new ArrayList<>(List.of(new ValidationErrorDTO("ERROR_CODE_1", "There is no such lesson!")))
        );
        when(getSubjectNameService
                .execute(any(GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceRequest.class)))
                .thenReturn(mockResponse);
    }

    private void mockSuccessfulRegistration() {
        GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceResponse mockResponse =
                new GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceResponse();
        mockResponse.setLessonName("Estonian language");

        when(getSubjectNameService
                .execute(any(GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceRequest.class)))
                .thenReturn(mockResponse);
    }

}
