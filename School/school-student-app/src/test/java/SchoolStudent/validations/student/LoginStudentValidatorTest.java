package SchoolStudent.validations.student;

import SchoolStudent.request.student.LoginStudentRequest;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolStudentParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginStudentValidatorTest {

    @Mock
    private ValidatorClassWithMethodsForSchoolStudentParameters validation;

    @InjectMocks
    private LoginStudentValidator validator;

    private LoginStudentRequest request;
    private ValidationError error;

    @BeforeEach
    void setUp() {
        request = new LoginStudentRequest();
        request.setPassword("password123");
        error = ValidationError.builder()
                .errorCode("SCHOOL_STUDENT_ERROR_CODE_8")
                .description("Password must not be empty")
                .build();
    }

    @Test
    void shouldReturnErrorIfPasswordIsEmpty() {
        request.setPassword("");
        when(validation.mustNotBeEmptyPassword("")).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorIfStudentDoesNotExist() {
        when(validation.mustNotBeEmptyPassword("password123")).thenReturn(Optional.empty());
        when(validation.suchStudentIsNotExists("password123")).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnEmptyListWhenPasswordIsValidAndStudentExists() {
        when(validation.mustNotBeEmptyPassword("password123")).thenReturn(Optional.empty());
        when(validation.suchStudentIsNotExists("password123")).thenReturn(Optional.empty());
        List<ValidationError> result = validator.validate(request);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
