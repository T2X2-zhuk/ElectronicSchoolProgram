package SchoolStudent.validations.schoolClass;

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
class GetSchoolClassIdsValidatorTest {

    @Mock
    private ValidatorClassWithMethodsForSchoolClassParameters classValidator;

    @InjectMocks
    private GetSchoolClassIdsValidator validator;

    private ValidationError error;

    @BeforeEach
    void setUp() {
        error = ValidationError.builder()
                .errorCode("ERROR_CODE")
                .description("Test error")
                .build();
    }

    @Test
    void shouldReturnErrorIfSchoolClassIdsListIsEmpty() {
        List<Long> ids = List.of();
        when(classValidator.validateSchoolClassIds(ids)).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(ids);
        assertEquals(1, result.size());
        assertEquals(error, result.get(0));
    }

    @Test
    void shouldReturnEmptyListIfSchoolClassIdsListIsNotEmpty() {
        List<Long> ids = List.of(1L, 2L, 3L);
        when(classValidator.validateSchoolClassIds(ids)).thenReturn(Optional.empty());
        List<ValidationError> result = validator.validate(ids);
        assertTrue(result.isEmpty());
    }
}
