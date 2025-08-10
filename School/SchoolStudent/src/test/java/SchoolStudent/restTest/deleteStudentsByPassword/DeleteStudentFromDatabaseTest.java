package SchoolStudent.restTest.deleteStudentsByPassword;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public abstract class DeleteStudentFromDatabaseTest extends DeleteStudentFromDatabaseTestCase{

    private static final String TEST_FILE_BASE_FOLDER = "forDeleteStudent";

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
