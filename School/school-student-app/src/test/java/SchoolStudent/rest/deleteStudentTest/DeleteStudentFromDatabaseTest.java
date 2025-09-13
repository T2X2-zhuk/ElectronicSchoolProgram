package SchoolStudent.rest.deleteStudentTest;

import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.DeleteStudentForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.TransferOfStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.DeleteStudentForSchoolLessonsAndCertificatesMicroserviceResponse;
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
public class DeleteStudentFromDatabaseTest extends DeleteStudentFromDatabaseTestCase{

    private static final String TEST_FILE_BASE_FOLDER = "studentFile/forDeleteStudent";
    @MockBean
    protected SchoolLessonsAndCertificatesMicroserviceForSaveStudent schoolLessonsAndCertificatesMicroserviceForSaveStudent;
    @MockBean
    protected SchoolLessonsAndCertificatesMicroserviceForTransferStudent transferStudent;
    @MockBean
    protected SchoolLessonsAndCertificatesMicroserviceForGetSubjectNameService getSubjectNameService;
    @MockBean
    protected SchoolLessonsAndCertificatesMicroserviceForDeleteStudents deleteStudents;

    @Test
    @DisplayName("ERROR_CODE_1_password_is_empty")
    public void check_ERROR_CODE_1() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_1_password_is_empty");
    }

    @Test
    @DisplayName("password_is_successful")
    public void successful() throws Exception {
        mockSuccessfulDelete();
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/password_is_successful");
    }

    private void mockSuccessfulDelete() {
        DeleteStudentForSchoolLessonsAndCertificatesMicroserviceResponse mockResponse =
                new DeleteStudentForSchoolLessonsAndCertificatesMicroserviceResponse();
        mockResponse.setMessage("Student/Students matching the specified passwords have been successfully deleted.");
                when(deleteStudents
                .execute(any(DeleteStudentForSchoolLessonsAndCertificatesMicroserviceRequest.class)))
                .thenReturn(mockResponse);
    }

}
