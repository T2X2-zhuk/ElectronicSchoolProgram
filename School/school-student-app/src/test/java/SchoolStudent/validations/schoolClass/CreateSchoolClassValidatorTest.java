package SchoolStudent.validations.schoolClass;

import SchoolStudent.jpa.dto.SchoolClassDTO;
import SchoolStudent.request.schoolClass.CreateSchoolClassRequest;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolClassParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
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
        schoolClassDTO = SchoolClassDTO.builder()
                .number(5L)
                .category("A")
                .build();

        request = new CreateSchoolClassRequest();
        request.setSchoolClassDTO(schoolClassDTO);
        error = ValidationError.builder()
                .errorCode("ERROR_CODE")
                .description("Test error")
                .build();
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNumbers")
    void shouldReturnError_whenNumberIsInvalid(Long input) {
        schoolClassDTO.setNumber(input);
        when(classValidator.numberMustNotBeEmpty(input))
                .thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.get(0));
    }

    static Long[] provideInvalidNumbers() {
        return new Long[]{null};
    }

    @ParameterizedTest
    @CsvSource({
            ", ERROR_CODE",
            "'', ERROR_CODE",
            "' ', ERROR_CODE"
    })
    void shouldReturnError_whenCategoryIsInvalid(String category, String expectedCode) {
        schoolClassDTO.setCategory(category);
        when(classValidator.categoryMustNotBeEmpty(category))
                .thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.get(0));
    }

    @ParameterizedTest
    @CsvSource({
            "B",
            "C"
    })
    void shouldReturnEmpty_whenCategoryIsValid(String category) {
        schoolClassDTO.setCategory(category);
        when(classValidator.numberMustNotBeEmpty(schoolClassDTO.getNumber()))
                .thenReturn(Optional.empty());
        when(classValidator.categoryMustNotBeEmpty(category))
                .thenReturn(Optional.empty());
        when(classValidator.fieldCategoryClassMustContainOneCapitalEnglishLetter(category))
                .thenReturn(Optional.empty());
        when(classValidator.suchSchoolClassAlreadyExist(schoolClassDTO.getNumber(), category))
                .thenReturn(Optional.empty());

        schoolClassDTO.setCategory(category);
        List<ValidationError> result = validator.validate(request);

        assertTrue(result.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideSchoolClassExistence")
    void shouldReturnError_whenSchoolClassAlreadyExists(boolean exists) {
        when(classValidator.numberMustNotBeEmpty(schoolClassDTO.getNumber()))
                .thenReturn(Optional.empty());
        when(classValidator.categoryMustNotBeEmpty(schoolClassDTO.getCategory()))
                .thenReturn(Optional.empty());
        when(classValidator.fieldCategoryClassMustContainOneCapitalEnglishLetter(schoolClassDTO.getCategory()))
                .thenReturn(Optional.empty());
        when(classValidator.suchSchoolClassAlreadyExist(schoolClassDTO.getNumber(), schoolClassDTO.getCategory()))
                .thenReturn(exists ? Optional.of(error) : Optional.empty());
        List<ValidationError> result = validator.validate(request);
        if (exists) {
            assertEquals(1, result.size());
            assertEquals(error, result.get(0));
        } else {
            assertTrue(result.isEmpty());
        }
    }

    static Boolean[] provideSchoolClassExistence() {
        return new Boolean[]{true, false};
    }
}
