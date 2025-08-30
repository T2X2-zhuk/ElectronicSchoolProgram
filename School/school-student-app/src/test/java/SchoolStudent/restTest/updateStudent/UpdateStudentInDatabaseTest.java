package SchoolStudent.restTest.updateStudent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class UpdateStudentInDatabaseTest extends UpdateStudentInDatabaseTestCase {

    private static final String TEST_FILE_BASE_FOLDER = "studentFile/forUpdateStudent";

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
    @DisplayName("Successful")
    public void check_ERROR_CODE_4() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/Successful");
    }
}
