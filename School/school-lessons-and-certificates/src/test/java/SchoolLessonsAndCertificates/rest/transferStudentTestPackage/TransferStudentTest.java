package SchoolLessonsAndCertificates.rest.transferStudentTestPackage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class TransferStudentTest extends TransferStudentTestCase {

   private static final String TEST_FILE_BASE_FOLDER = "forTransferStudent";

   @Test
   @DisplayName("check_Successful")
   public void check_Successful() throws Exception {
       executeAndCompare(TEST_FILE_BASE_FOLDER + "/successful");
   }
}
