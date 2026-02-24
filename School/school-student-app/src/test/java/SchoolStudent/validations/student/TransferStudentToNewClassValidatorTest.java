package SchoolStudent.validations.student;

import SchoolStudent.jpa.dto.SchoolClassDTO;
import SchoolStudent.request.student.TransferStudentToNewClassRequest;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolClassParameters;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolStudentParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class TransferStudentToNewClassValidatorTest {

    @Mock
    private ValidatorClassWithMethodsForSchoolStudentParameters studentValidator;
    @Mock
    private ValidatorClassWithMethodsForSchoolClassParameters classValidator;
    @InjectMocks
    private TransferStudentToNewClassValidator validator;

    private TransferStudentToNewClassRequest request;
    private SchoolClassDTO schoolClassDTO;
    private ValidationError error;

    @BeforeEach
    void setUp() {
        schoolClassDTO = SchoolClassDTO.builder().number(5L).category("A").build();
        request = new TransferStudentToNewClassRequest();
        request.setEmail("student@mail.com");
        request.setSchoolClassDTO(schoolClassDTO);
        error = ValidationError.builder()
                .errorCode("ERROR_CODE")
                .description("Test error")
                .build();
    }

    @Test
    void shouldReturnErrorIfEmailIsEmpty() {
        request.setEmail("");
        when(studentValidator.mustNotBeEmptyEmail("")).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorIfStudentEmailDoesNotExist() {
        when(studentValidator.mustNotBeEmptyEmail("student@mail.com")).thenReturn(Optional.empty());
        when(studentValidator.areThereAnyEmailAddressesOfStudentsOnTheList(List.of("student@mail.com"))).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorIfSchoolClassNumberIsEmpty() {
        schoolClassDTO.setNumber(null);
        when(classValidator.numberMustNotBeEmpty(null)).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorIfSchoolClassCategoryIsEmpty() {
        schoolClassDTO.setCategory(null);
        when(classValidator.categoryMustNotBeEmpty(null)).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorIfSchoolClassDoesNotExist() {
        when(classValidator.numberMustNotBeEmpty(5L)).thenReturn(Optional.empty());
        when(classValidator.categoryMustNotBeEmpty("A")).thenReturn(Optional.empty());
        when(classValidator.fieldCategoryClassMustContainOneCapitalEnglishLetter("A")).thenReturn(Optional.empty());
        when(classValidator.suchSchoolClassIsNotExist(5L, "A")).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnEmptyListWhenAllValidationsPass() {
        when(studentValidator.mustNotBeEmptyEmail("student@mail.com")).thenReturn(Optional.empty());
        when(studentValidator.areThereAnyEmailAddressesOfStudentsOnTheList(List.of("student@mail.com"))).thenReturn(Optional.empty());
        when(classValidator.numberMustNotBeEmpty(5L)).thenReturn(Optional.empty());
        when(classValidator.categoryMustNotBeEmpty("A")).thenReturn(Optional.empty());
        when(classValidator.fieldCategoryClassMustContainOneCapitalEnglishLetter("A")).thenReturn(Optional.empty());
        when(classValidator.suchSchoolClassIsNotExist(5L, "A")).thenReturn(Optional.empty());
        List<ValidationError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }
}
