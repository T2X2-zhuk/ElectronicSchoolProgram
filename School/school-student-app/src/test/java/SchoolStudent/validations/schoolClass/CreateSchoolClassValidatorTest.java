package SchoolStudent.validations.schoolClass;

import SchoolStudent.jpa.dto.SchoolClassDTO;
import SchoolStudent.request.schoolClass.CreateSchoolClassRequest;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolClassParameters;
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
class CreateSchoolClassValidatorTest {

    @Mock
    private ValidatorClassWithMethodsForSchoolClassParameters classValidator;

    @InjectMocks
    private CreateSchoolClassValidator validator;

    private CreateSchoolClassRequest request;
    private SchoolClassDTO schoolClassDTO;
    private ValidationError error;

    @BeforeEach
    void setUp() {
        schoolClassDTO = SchoolClassDTO.builder().number(5L).category("A").build();
        request = new CreateSchoolClassRequest();
        request.setSchoolClassDTO(schoolClassDTO);

        error = ValidationError.builder()
                .errorCode("ERROR_CODE")
                .description("Test error")
                .build();
    }

    @Test
    void shouldReturnErrorIfNumberIsEmpty() {
        schoolClassDTO.setNumber(null);
        when(classValidator.numberMustNotBeEmpty(null)).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.get(0));
    }

    @Test
    void shouldReturnErrorIfCategoryIsEmpty() {
        schoolClassDTO.setCategory(null);
        when(classValidator.categoryMustNotBeEmpty(null)).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.get(0));
    }

    @Test
    void shouldReturnErrorIfCategoryInvalid() {
        when(classValidator.numberMustNotBeEmpty(5L)).thenReturn(Optional.empty());
        when(classValidator.categoryMustNotBeEmpty("A")).thenReturn(Optional.empty());
        when(classValidator.fieldCategoryClassMustContainOneCapitalEnglishLetter("A")).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.get(0));
    }

    @Test
    void shouldReturnErrorIfSchoolClassAlreadyExists() {
        when(classValidator.numberMustNotBeEmpty(5L)).thenReturn(Optional.empty());
        when(classValidator.categoryMustNotBeEmpty("A")).thenReturn(Optional.empty());
        when(classValidator.fieldCategoryClassMustContainOneCapitalEnglishLetter("A")).thenReturn(Optional.empty());
        when(classValidator.suchSchoolClassAlreadyExist(5L, "A")).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.get(0));
    }

    @Test
    void shouldReturnEmptyListWhenAllValidationsPass() {
        when(classValidator.numberMustNotBeEmpty(5L)).thenReturn(Optional.empty());
        when(classValidator.categoryMustNotBeEmpty("A")).thenReturn(Optional.empty());
        when(classValidator.fieldCategoryClassMustContainOneCapitalEnglishLetter("A")).thenReturn(Optional.empty());
        when(classValidator.suchSchoolClassAlreadyExist(5L, "A")).thenReturn(Optional.empty());
        List<ValidationError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }
}
