package SchoolStudent.validations.MethodsValidatorClasses;
import SchoolStudent.jpa.domain.Teacher;
import SchoolStudent.jpa.repositories.TeacherRepository;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorClassWithMethodsForTeacherParametersTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private ValidatorClassWithMethodsForTeacherParameters validator;

    private ValidationError error;

    @BeforeEach
    void setUp() {
        error = ValidationError.builder()
                .errorCode("TEST_ERROR")
                .description("Test error")
                .build();
        lenient().when(errorFactory.buildError(anyString()))
                .thenReturn(error);
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

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" "})
        void subject_shouldReturnError_whenInvalid(String input) {
            assertTrue(validator.subjectMustNotBeEmpty(input).isPresent());
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
            when(teacherRepository.findByEmail("test@test.com"))
                    .thenReturn(Optional.of(mock(Teacher.class)));
            assertTrue(validator.suchEmailAlreadyExists("test@test.com").isPresent());
        }

        @Test
        void shouldReturnEmpty_whenEmailNotExists() {
            when(teacherRepository.findByEmail("test@test.com"))
                    .thenReturn(Optional.empty());
            assertTrue(validator.suchEmailAlreadyExists("test@test.com").isEmpty());
        }
    }

    @Nested
    class PasswordValidation {

        @Test
        void shouldReturnError_whenPasswordAlreadyExists() {
            when(teacherRepository.findByPassword("123"))
                    .thenReturn(Optional.of(mock(Teacher.class)));
            assertTrue(validator.suchPasswordAlreadyExists("123").isPresent());
        }

        @Test
        void shouldReturnEmpty_whenPasswordNotExists() {
            when(teacherRepository.findByPassword("123"))
                    .thenReturn(Optional.empty());
            assertTrue(validator.suchPasswordAlreadyExists("123").isEmpty());
        }
    }
}
