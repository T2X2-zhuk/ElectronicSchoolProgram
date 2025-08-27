package SchoolStudent.restTest.saveStudentInDatabase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


 class SaveStudentInDatabaseTest extends SaveStudentInDatabaseTestCase {

    private static final String TEST_FILE_BASE_FOLDER = "forSaveStudent";

    @Test
    @DisplayName("ERROR_CODE_1_first_name_must_not_be_null")
    public void check_ERROR_CODE_1() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_1_first_name_must_not_be_null");
    }

    @Test
    @DisplayName("ERROR_CODE_2_last_name_must_not_be_empty")
    public void check_ERROR_CODE_2() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_2_last_name_must_not_be_empty");
    }

    @Test
    @DisplayName("field_fatherland_must_not_be_empty")
    public void check_ERROR_CODE_3() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_3_field_fatherland_must_not_be_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_5_field_email_must_not_be_empty")
    public void check_ERROR_CODE_5() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_5_field_email_must_not_be_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_6_such_email_already_exists")
    public void check_ERROR_CODE_6() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_6_such_email_already_exists");
    }



    @Test
    @DisplayName("ERROR_CODE_7_email_your_email_is_not_correct")
    public void check_ERROR_CODE_7() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_7_email_your_email_is_not_correct");
    }

    @Test
    @DisplayName("ERROR_CODE_8_field_password_must_not_be_empty")
    public void check_ERROR_CODE_8() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_8_field_password_must_not_be_empty");
    }



    @Test
    @DisplayName("ERROR_CODE_9_such_password_already_exists")
    public void check_ERROR_CODE_9() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_9_such_password_already_exists");
    }

    @Test
    @DisplayName("ERROR_CODE_10_field_number_class_must_not_be_empty")
    public void check_ERROR_CODE_10() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_10_field_number_class_must_not_be_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_11_number_class_must_be_1-11")
    public void check_ERROR_CODE_11() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_11_number_class_must_be_1-11");
    }

    @Test
    @DisplayName("ERROR_CODE_12_field_category_class_must_not_be_empty")
    public void check_ERROR_CODE_12() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_12_field_category_class_must_not_be_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_13_field_category_class_must_contain_one_capital_english_letter")
    public void check_ERROR_CODE_13() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_13_field_category_class_must_contain_one_capital_english_letter");
    }

    @Test
    @DisplayName("ERROR_CODE_14_such_category_class_is_not_in_database")
    public void check_ERROR_CODE_14() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_14_such_category_class_is_not_in_database");
    }

    @Test
    @DisplayName("ERROR_CODE_15_field_specific_code_for_registration_must_not_be_empty")
    public void check_ERROR_CODE_15() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_15_field_specific_code_for_registration_must_not_be_empty");
    }



    @Test
    @DisplayName("ERROR_CODE_16_the_wrong_specific_code")
    public void check_ERROR_CODE_16() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_16_the_wrong_specific_code");
    }

    @Test
    @DisplayName("check_Successful")
    public void check_Successful() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/17_check_Successful");
    }
}
