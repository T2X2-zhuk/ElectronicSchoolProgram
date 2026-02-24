package SchoolStudent.validations.MethodsValidatorClasses;

import SchoolStudent.jpa.domain.SpecificCodeForRegistrationInElectronicSchool;
import SchoolStudent.jpa.repositories.CodeForRegistrationRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParametersTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @Mock
    private CodeForRegistrationRepository code;

    @InjectMocks
    private ValidatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters validator;

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
    class SpecialCodeEmptyValidation {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" "})
        void shouldReturnError_whenCodeIsNullOrBlank(String input) {
            assertTrue(
                    validator.mustNotBeEmptySpecialCode(input)
                            .isPresent()
            );
        }

        @Test
        void shouldReturnEmpty_whenCodeIsValid() {
            assertTrue(
                    validator.mustNotBeEmptySpecialCode("VALID_CODE")
                            .isEmpty()
            );
        }
    }

    @Nested
    class StudentCodeValidation {

        @Test
        void shouldReturnError_whenStudentCodeNotFound() {
            when(code.findBySpecificCodeForRegistrationForStudent("CODE"))
                    .thenReturn(Optional.empty());
            assertTrue(
                    validator.validateSpecificCodeForStudent("CODE")
                            .isPresent()
            );
        }

        @Test
        void shouldReturnEmpty_whenStudentCodeExists() {
            when(code.findBySpecificCodeForRegistrationForStudent("CODE"))
                    .thenReturn(Optional.of(mock(SpecificCodeForRegistrationInElectronicSchool.class)));
            assertTrue(
                    validator.validateSpecificCodeForStudent("CODE")
                            .isEmpty()
            );
        }
    }

    @Nested
    class TeacherCodeValidation {

        @Test
        void shouldReturnError_whenTeacherCodeNotFound() {
            when(code.findBySpecificCodeForRegistrationForTeacher("CODE"))
                    .thenReturn(Optional.empty());
            assertTrue(
                    validator.validateSpecificCodeForTeacher("CODE")
                            .isPresent()
            );
        }

        @Test
        void shouldReturnEmpty_whenTeacherCodeExists() {
            when(code.findBySpecificCodeForRegistrationForTeacher("CODE"))
                    .thenReturn(Optional.of(mock(SpecificCodeForRegistrationInElectronicSchool.class)));
            assertTrue(
                    validator.validateSpecificCodeForTeacher("CODE")
                            .isEmpty()
            );
        }
    }
}
