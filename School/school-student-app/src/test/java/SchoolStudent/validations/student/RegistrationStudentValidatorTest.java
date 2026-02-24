package SchoolStudent.validations.student;

import SchoolStudent.jpa.dto.SchoolClassDTO;
import SchoolStudent.jpa.dto.SchoolStudentDTO;
import SchoolStudent.jpa.dto.SpecificCodeForRegistrationInElectronicSchoolDTO;
import SchoolStudent.request.student.RegistrationStudentRequest;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolClassParameters;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolStudentParameters;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters;
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
class RegistrationStudentValidatorTest {

    @Mock
    private ValidatorClassWithMethodsForSchoolStudentParameters studentValidator;
    @Mock
    private ValidatorClassWithMethodsForSchoolClassParameters classValidator;
    @Mock
    private ValidatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters codeValidator;

    @InjectMocks
    private RegistrationStudentValidator validator;

    private RegistrationStudentRequest request;
    private SchoolStudentDTO studentDTO;
    private SpecificCodeForRegistrationInElectronicSchoolDTO codeDTO;
    private ValidationError error;

    @BeforeEach
    void setUp() {
        studentDTO = new SchoolStudentDTO();
        studentDTO.setFirstName("John");
        studentDTO.setLastName("Doe");
        studentDTO.setFatherland("Smith");
        studentDTO.setEmail("john.doe@mail.com");
        studentDTO.setPassword("password123");
        studentDTO.setSchoolClassDTO(SchoolClassDTO.builder().number(5L).category("A").build());
        codeDTO = new SpecificCodeForRegistrationInElectronicSchoolDTO();
        codeDTO.setSpecificCodeForRegistrationForStudent("SPECIAL123");
        request = new RegistrationStudentRequest();
        request.setSchoolStudentDTO(studentDTO);
        request.setSpecificCodeForRegistrationInElectronicSchoolDTO(codeDTO);
        error = ValidationError.builder()
                .errorCode("ERROR_CODE")
                .description("Test error")
                .build();
    }

    @Test
    void shouldReturnErrorWhenFirstNameIsEmpty() {
        studentDTO.setFirstName("");
        when(studentValidator.firstNameMustNotBeEmpty("")).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorWhenEmailIsInvalid() {
        when(studentValidator.mustNotBeEmptyEmail("john.doe@mail.com")).thenReturn(Optional.empty());
        when(studentValidator.emailYourEmailIsNotCorrectError("john.doe@mail.com")).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorWhenPasswordAlreadyExists() {
        when(studentValidator.mustNotBeEmptyPassword("password123")).thenReturn(Optional.empty());
        when(studentValidator.suchPasswordAlreadyExists("password123")).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorWhenSpecificCodeInvalid() {
        when(codeValidator.mustNotBeEmptySpecialCode("SPECIAL123")).thenReturn(Optional.empty());
        when(codeValidator.validateSpecificCodeForStudent("SPECIAL123")).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorWhenSchoolClassDoesNotExist() {
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
        when(studentValidator.firstNameMustNotBeEmpty("John")).thenReturn(Optional.empty());
        when(studentValidator.lastNameMustNotBeEmpty("Doe")).thenReturn(Optional.empty());
        when(studentValidator.fatherlandMustNotBeEmpty("Smith")).thenReturn(Optional.empty());
        when(studentValidator.mustNotBeEmptyEmail("john.doe@mail.com")).thenReturn(Optional.empty());
        when(studentValidator.emailYourEmailIsNotCorrectError("john.doe@mail.com")).thenReturn(Optional.empty());
        when(studentValidator.suchEmailAlreadyExists("john.doe@mail.com")).thenReturn(Optional.empty());
        when(studentValidator.mustNotBeEmptyPassword("password123")).thenReturn(Optional.empty());
        when(studentValidator.suchPasswordAlreadyExists("password123")).thenReturn(Optional.empty());
        when(codeValidator.mustNotBeEmptySpecialCode("SPECIAL123")).thenReturn(Optional.empty());
        when(codeValidator.validateSpecificCodeForStudent("SPECIAL123")).thenReturn(Optional.empty());
        when(classValidator.numberMustNotBeEmpty(5L)).thenReturn(Optional.empty());
        when(classValidator.categoryMustNotBeEmpty("A")).thenReturn(Optional.empty());
        when(classValidator.fieldCategoryClassMustContainOneCapitalEnglishLetter("A")).thenReturn(Optional.empty());
        when(classValidator.suchSchoolClassIsNotExist(5L, "A")).thenReturn(Optional.empty());
        List<ValidationError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }
}
