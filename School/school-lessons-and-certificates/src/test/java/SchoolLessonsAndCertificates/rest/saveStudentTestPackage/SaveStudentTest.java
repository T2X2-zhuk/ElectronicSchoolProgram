package SchoolLessonsAndCertificates.rest.saveStudentTestPackage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


 class SaveStudentTest extends SaveStudentTestCase {

    private static final String TEST_FILE_BASE_FOLDER = "forSaveStudent";

    @Test
    @DisplayName("check_Successful")
    public void check_Successful() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/successful");
    }
}
