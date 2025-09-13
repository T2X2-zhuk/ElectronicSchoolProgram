package SchoolStudent.rest.updateStudent;

import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.TransferOfStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.TransferStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceResponse;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForDeleteStudents;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForGetSubjectNameService;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForSaveStudent;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForTransferStudent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class UpdateStudentInDatabaseTest extends UpdateStudentInDatabaseTestCase {

    private static final String TEST_FILE_BASE_FOLDER = "studentFile/forUpdateStudent";
    @MockBean
    protected SchoolLessonsAndCertificatesMicroserviceForSaveStudent schoolLessonsAndCertificatesMicroserviceForSaveStudent;
    @MockBean
    protected SchoolLessonsAndCertificatesMicroserviceForTransferStudent transferStudent;
    @MockBean
    protected SchoolLessonsAndCertificatesMicroserviceForGetSubjectNameService getSubjectNameService;
    @MockBean
    protected SchoolLessonsAndCertificatesMicroserviceForDeleteStudents deleteStudents;
    @Test
    @DisplayName("rest/studentFile/forUpdateStudent/ERROR_CODE_1_all_is_empty")
    public void check_ERROR_CODE_1() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_1_all_is_empty");
    }

    @Test
    @DisplayName("rest/studentFile/forUpdateStudent/ERROR_CODE_2_not_such_student_with_so_email")
    public void check_ERROR_CODE_2() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_2_not_such_student_with_so_email");
    }
    @Test
    @DisplayName("rest/studentFile/forUpdateStudent/ERROR_CODE_3_class_number_must_be_1-11")
    public void check_ERROR_CODE_3() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_3_class_number_must_be_1-11");
    }

    @Test
    @DisplayName("successful")
    public void check_ERROR_CODE_4() throws Exception {
        mockSuccessfulTransfer();
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/successful");
    }

    private void mockSuccessfulTransfer() {
        TransferStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceResponse mockResponse =
                new TransferStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceResponse();
        mockResponse.setMessage("Student with email bornr5606@gmail.com successfully transferred to 11 A class!");
        when(transferStudent
                .execute(any(TransferOfStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceRequest.class)))
                .thenReturn(mockResponse);
    }
}
