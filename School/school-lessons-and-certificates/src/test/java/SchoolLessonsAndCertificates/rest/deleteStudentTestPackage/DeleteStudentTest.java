package SchoolLessonsAndCertificates.rest.deleteStudentTestPackage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class DeleteStudentTest extends DeleteStudentTestCase {

   private static final String TEST_FILE_BASE_FOLDER = "forDeleteStudent";

   @Test
   @DisplayName("check_Successful")
   public void check_Successful() throws Exception {
       executeAndCompare(TEST_FILE_BASE_FOLDER + "/successful");
   }
}
