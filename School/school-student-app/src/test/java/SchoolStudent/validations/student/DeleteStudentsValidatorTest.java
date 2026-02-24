package SchoolStudent.validations.student;

import SchoolStudent.request.student.DeleteStudentsRequest;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteStudentsValidatorTest {

    @Mock
    private ValidatorClassWithMethodsForSchoolStudentParameters validation;

    @InjectMocks
    private DeleteStudentsValidator deleteStudentsValidator;

    private DeleteStudentsRequest request;
    private ValidationError error;

    @BeforeEach
    void setUp() {
        request = mock(DeleteStudentsRequest.class);
        error = ValidationError.builder()
                .errorCode("SCHOOL_STUDENT_ERROR_CODE_4")
                .description("You have not entered a single email that is in the database!")
                .build();
    }

    @Test
    void shouldReturnErrorWhenNoStudentsExist() {
        List<String> emails = List.of("test@mail.com");
        when(request.getEmails()).thenReturn(emails);
        when(validation.areThereAnyEmailAddressesOfStudentsOnTheList(emails))
                .thenReturn(Optional.of(error));
        List<ValidationError> result = deleteStudentsValidator.validate(request);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnEmptyListWhenStudentsExist() {
        List<String> emails = List.of("test@mail.com");
        when(request.getEmails()).thenReturn(emails);
        when(validation.areThereAnyEmailAddressesOfStudentsOnTheList(emails))
                .thenReturn(Optional.empty());
        List<ValidationError> result = deleteStudentsValidator.validate(request);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
