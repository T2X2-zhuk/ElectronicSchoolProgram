package SchoolStudent.validations.student;

import SchoolStudent.jpa.dto.SchoolClassDTO;
import SchoolStudent.request.student.GetStudentsBySchoolClassRequest;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetStudentsBySchoolClassValidatorTest {

    @Mock
    private ValidatorClassWithMethodsForSchoolClassParameters classValidator;
    @Mock
    private ValidatorClassWithMethodsForSchoolStudentParameters studentValidator;

    @InjectMocks
    private GetStudentsBySchoolClassValidator validator;

    private GetStudentsBySchoolClassRequest request;
    private SchoolClassDTO schoolClassDTO;
    private ValidationError error;

    @BeforeEach
    void setUp() {
        schoolClassDTO = new SchoolClassDTO();
        schoolClassDTO.setNumber(5L);
        schoolClassDTO.setCategory("A");
        error = ValidationError.builder()
                .errorCode("SCHOOL_STUDENT_ERROR_CODE_22")
                .description("School class does not exist")
                .build();
        request = new GetStudentsBySchoolClassRequest();
        request.setSchoolClassDTO(schoolClassDTO);
    }

    private void mockAllClassChecksPass() {
        when(classValidator.numberMustNotBeEmpty(schoolClassDTO.getNumber())).thenReturn(Optional.empty());
        when(classValidator.categoryMustNotBeEmpty(schoolClassDTO.getCategory())).thenReturn(Optional.empty());
        when(classValidator.fieldCategoryClassMustContainOneCapitalEnglishLetter(schoolClassDTO.getCategory())).thenReturn(Optional.empty());
        when(classValidator.suchSchoolClassIsNotExist(schoolClassDTO.getNumber(), schoolClassDTO.getCategory())).thenReturn(Optional.empty());
    }

    @Test
    void shouldReturnErrorIfSchoolClassNumberIsEmpty() {
        schoolClassDTO.setNumber(null);
        when(classValidator.numberMustNotBeEmpty(null)).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validationErrorDTOSList(request);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorIfSchoolClassCategoryIsEmpty() {
        schoolClassDTO.setCategory(null);
        when(classValidator.categoryMustNotBeEmpty(null)).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validationErrorDTOSList(request);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorIfSchoolClassDoesNotExist() {
        mockAllClassChecksPass();
        when(classValidator.suchSchoolClassIsNotExist(schoolClassDTO.getNumber(), schoolClassDTO.getCategory()))
                .thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validationErrorDTOSList(request);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorIfNoStudentsExistInClass() {
        mockAllClassChecksPass();
        when(studentValidator.checkStudentsExistInClass(schoolClassDTO.getNumber(), schoolClassDTO.getCategory()))
                .thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validationErrorDTOSList(request);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnEmptyListWhenEverythingIsValid() {
        mockAllClassChecksPass();
        when(studentValidator.checkStudentsExistInClass(schoolClassDTO.getNumber(), schoolClassDTO.getCategory()))
                .thenReturn(Optional.empty());
        List<ValidationError> result = validator.validationErrorDTOSList(request);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
