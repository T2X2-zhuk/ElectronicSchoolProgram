package SchoolStudent.rest.deleteStudentTest;

import SchoolStudent.core.SchoolLessonsAndCertificates.SchoolLessonsAndCertificatesMicroservice;
import SchoolStudent.core.dto.StudentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class DeleteStudentFromDatabaseTest extends DeleteStudentFromDatabaseTestCase{

    private static final String TEST_FILE_BASE_FOLDER = "studentFile/forDeleteStudent";
    @MockBean
    protected SchoolLessonsAndCertificatesMicroservice schoolLessonsAndCertificatesMicroservice;

    @BeforeEach
    void setUp() {
        when(schoolLessonsAndCertificatesMicroservice.execute(any(StudentDTO.class)))
                .thenReturn(true);
    }
    @Test
    @DisplayName("ERROR_CODE_1_password_is_empty")
    public void check_ERROR_CODE_1() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_1_password_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_2_password_is_successful")
    public void successful() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_2_password_is_successful");
    }

}
