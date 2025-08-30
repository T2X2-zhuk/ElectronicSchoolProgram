package SchoolStudent.restTest.GetAllStudentTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class GetAllStudentsTest extends GetAllStudentsTestCase {

   private static final String TEST_FILE_BASE_FOLDER = "studentFile/forGetStudents";

   @Test
   @DisplayName("errorCodeAllIsEmpty")
   public void check_ERROR_CODE_1() throws Exception {
       executeAndCompare(TEST_FILE_BASE_FOLDER + "/errorCodeAllIsEmpty");
   }

   @Test
   @DisplayName("NotSuchSchoolClassAndOneCapitalEnglishLetter")
   public void check_ERROR_CODE_2() throws Exception {
      executeAndCompare(TEST_FILE_BASE_FOLDER + "/errorCodeNotSuchSchoolClassAndFieldCategoryClassMustContainOneCapitalEnglishLetter");
   }

   @Test
   @DisplayName("categoryClassIsNotExist")
   public void errorCodeSuchCategoryClassIsNotExist() throws Exception {
      executeAndCompare(TEST_FILE_BASE_FOLDER + "/errorCodeSuchCategoryClassIsNotExist");
   }

   @Test
   @DisplayName("getSuccesfulStudent")
   public void successful() throws Exception {
      executeAndCompare(TEST_FILE_BASE_FOLDER + "/getSuccesfulStudent");
   }

}
