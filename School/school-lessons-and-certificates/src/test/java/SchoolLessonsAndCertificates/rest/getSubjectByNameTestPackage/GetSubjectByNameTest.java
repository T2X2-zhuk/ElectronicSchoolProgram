package SchoolLessonsAndCertificates.rest.getSubjectByNameTestPackage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


 class GetSubjectByNameTest extends GetSubjectByNameTestCase {

    private static final String TEST_FILE_BASE_FOLDER = "forGetSubjectByName";

    @Test
    @DisplayName("check_Successful")
    public void check_Successful() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/successful");
    }

     @Test
     @DisplayName("unsuccessful")
     public void unsuccessful() throws Exception {
         executeAndCompare(TEST_FILE_BASE_FOLDER + "/unsuccessful");
     }
}
