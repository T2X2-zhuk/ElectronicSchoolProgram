package SchoolStudent.rest.GetAllStudentTest;

import SchoolStudent.core.SchoolLessonsAndCertificates.SchoolLessonsAndCertificatesMicroservice;
import SchoolStudent.core.dto.StudentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class GetAllStudentsTest extends GetAllStudentsTestCase {

   private static final String TEST_FILE_BASE_FOLDER = "studentFile/forGetStudents";
   @MockBean
   protected SchoolLessonsAndCertificatesMicroservice schoolLessonsAndCertificatesMicroservice;

   @BeforeEach
   void setUp() {
      when(schoolLessonsAndCertificatesMicroservice.execute(any(StudentDTO.class)))
              .thenReturn(true);
   }
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
