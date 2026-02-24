package SchoolStudent.validations.MethodsValidatorClasses;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import SchoolStudent.jpa.repositories.SchoolStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidatorClassWithMethodsForSchoolStudentParametersTest {

    @Mock
    private ValidationErrorFactory errorFactory;
    @Mock
    private SchoolStudentRepository studentRepository;

    @InjectMocks
    private ValidatorClassWithMethodsForSchoolStudentParameters validator;

    private ValidationError error;

    @BeforeEach
    void setUp() {
        error = ValidationError.builder()
                .errorCode("TEST_ERROR")
                .description("Test error")
                .build();
        lenient().when(errorFactory.buildError(anyString())).thenReturn(error);
    }

    @Nested
    class TextFieldValidation {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" "})
        void firstName_shouldReturnError_whenInvalid(String input) {
            assertTrue(validator.firstNameMustNotBeEmpty(input).isPresent());
        }

        @Test
        void firstName_shouldReturnEmpty_whenValid() {
            assertTrue(validator.firstNameMustNotBeEmpty("John").isEmpty());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" "})
        void lastName_shouldReturnError_whenInvalid(String input) {
            assertTrue(validator.lastNameMustNotBeEmpty(input).isPresent());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" "})
        void fatherland_shouldReturnError_whenInvalid(String input) {
            assertTrue(validator.fatherlandMustNotBeEmpty(input).isPresent());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" "})
        void password_shouldReturnError_whenInvalid(String input) {
            assertTrue(validator.mustNotBeEmptyPassword(input).isPresent());
        }
    }

    @Nested
    class EmailValidation {

        @ParameterizedTest
        @ValueSource(strings = {
                "wrong",
                "wrong@",
                "wrong@test",
                "wrong@test."
        })
        void email_shouldReturnError_whenFormatInvalid(String email) {
            assertTrue(validator.emailYourEmailIsNotCorrectError(email).isPresent());
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "a@test.com",
                "john.doe@gmail.com"
        })
        void email_shouldReturnEmpty_whenFormatValid(String email) {
            assertTrue(validator.emailYourEmailIsNotCorrectError(email).isEmpty());
        }

        @Test
        void shouldReturnError_whenEmailAlreadyExists() {
            when(studentRepository.existsByEmail("test@test.com")).thenReturn(true);
            assertTrue(validator.suchEmailAlreadyExists("test@test.com").isPresent());
        }

        @Test
        void shouldReturnEmpty_whenEmailNotExists() {
            when(studentRepository.existsByEmail("test@test.com")).thenReturn(false);

            assertTrue(validator.suchEmailAlreadyExists("test@test.com").isEmpty());
        }
    }

    @Nested
    class PasswordValidation {

        @Test
        void shouldReturnError_whenPasswordAlreadyExists() {
            when(studentRepository.existsByPassword("123")).thenReturn(true);
            assertTrue(validator.suchPasswordAlreadyExists("123").isPresent());
        }

        @Test
        void shouldReturnError_whenStudentDoesNotExist() {
            when(studentRepository.existsByPassword("123")).thenReturn(false);
            assertTrue(validator.suchStudentIsNotExists("123").isPresent());
        }
    }

    @Nested
    class EmailListValidation {

        @Test
        void shouldReturnError_whenNoEmailsExist() {
            when(studentRepository.existsByEmail(anyString())).thenReturn(false);
            Optional<ValidationError> result =
                    validator.areThereAnyEmailAddressesOfStudentsOnTheList(
                            List.of("a@test.com", "b@test.com")
                    );
            assertTrue(result.isPresent());
        }

        @Test
        void shouldReturnEmpty_whenAtLeastOneEmailExists() {
            when(studentRepository.existsByEmail("a@test.com")).thenReturn(true);
            when(studentRepository.existsByEmail("b@test.com")).thenReturn(false);
            Optional<ValidationError> result =
                    validator.areThereAnyEmailAddressesOfStudentsOnTheList(
                            List.of("a@test.com", "b@test.com")
                    );
            assertTrue(result.isEmpty());
        }
    }

    @Nested
    class SchoolClassValidation {

        @Test
        void shouldReturnError_whenNoStudentsInClass() {
            when(studentRepository
                    .existsBySchoolClass_NumberAndSchoolClass_Category(5L, "A"))
                    .thenReturn(false);
            assertTrue(validator.checkStudentsExistInClass(5L, "A").isPresent());
        }

        @Test
        void shouldReturnEmpty_whenStudentsExistInClass() {
            when(studentRepository
                    .existsBySchoolClass_NumberAndSchoolClass_Category(5L, "A"))
                    .thenReturn(true);
            assertTrue(validator.checkStudentsExistInClass(5L, "A").isEmpty());
        }
    }
}
