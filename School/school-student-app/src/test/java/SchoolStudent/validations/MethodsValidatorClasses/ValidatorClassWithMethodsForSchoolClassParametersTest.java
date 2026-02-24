package SchoolStudent.validations.MethodsValidatorClasses;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import SchoolStudent.jpa.repositories.SchoolClassRepository;
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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ValidatorClassWithMethodsForSchoolClassParametersTest {

    @Mock
    private ValidationErrorFactory errorFactory;
    @Mock
    private SchoolClassRepository schoolClassRepository;

    @InjectMocks
    private ValidatorClassWithMethodsForSchoolClassParameters validator;

    private ValidationError error;

    @BeforeEach
    void setUp() {
        error = ValidationError.builder()
                .errorCode("TEST_ERROR")
                .description("Test error")
                .build();
        lenient().when(errorFactory.buildError(anyString())).thenReturn(error);
    }

    @Nested
    class NumberValidation {

        @Test
        void shouldReturnError_whenNumberIsNull() {
            assertTrue(validator.numberMustNotBeEmpty(null).isPresent());
        }

        @Test
        void shouldReturnEmpty_whenNumberIsNotNull() {
            assertTrue(validator.numberMustNotBeEmpty(5L).isEmpty());
        }
    }

    @Nested
    class CategoryValidation {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" "})
         void shouldReturnError_whenCategoryIsInvalid(String input) {
            assertTrue(validator.categoryMustNotBeEmpty(input).isPresent());
        }

        @Test
        void shouldReturnEmpty_whenCategoryIsValid() {
            assertTrue(validator.categoryMustNotBeEmpty("A").isEmpty());
        }
    }

    @Nested
    class FieldCategoryFormatValidation {

        @ParameterizedTest
        @ValueSource(strings = {"a", "AB", ""})
        void shouldReturnError_whenCategoryFormatInvalid(String input) {
            assertTrue(
                    validator.fieldCategoryClassMustContainOneCapitalEnglishLetter(input)
                            .isPresent()
            );
        }

        @Test
        void shouldReturnEmpty_whenCategoryFormatValid() {
            assertTrue(
                    validator.fieldCategoryClassMustContainOneCapitalEnglishLetter("B")
                            .isEmpty()
            );
        }
    }

    @Nested
    class SchoolClassExistenceValidation {

        @Test
        void shouldReturnError_whenClassDoesNotExist() {
            when(schoolClassRepository.existsByNumberAndCategory(5L, "A"))
                    .thenReturn(false);
            assertTrue(
                    validator.suchSchoolClassIsNotExist(5L, "A")
                            .isPresent()
            );
        }

        @Test
        void shouldReturnEmpty_whenClassExists() {
            when(schoolClassRepository.existsByNumberAndCategory(5L, "A"))
                    .thenReturn(true);
            assertTrue(
                    validator.suchSchoolClassIsNotExist(5L, "A")
                            .isEmpty()
            );
        }

        @Test
        void shouldReturnError_whenClassAlreadyExists() {
            when(schoolClassRepository.existsByNumberAndCategory(5L, "A"))
                    .thenReturn(true);
            assertTrue(
                    validator.suchSchoolClassAlreadyExist(5L, "A")
                            .isPresent()
            );
        }

        @Test
        void shouldReturnEmpty_whenClassDoesNotAlreadyExist() {
            when(schoolClassRepository.existsByNumberAndCategory(5L, "A"))
                    .thenReturn(false);
            assertTrue(
                    validator.suchSchoolClassAlreadyExist(5L, "A")
                            .isEmpty()
            );
        }
    }

    @Nested
    class SchoolClassIdsValidation {

        @Test
        void shouldReturnError_whenListIsEmpty() {
            assertTrue(
                    validator.validateSchoolClassIds(List.of())
                            .isPresent()
            );
        }

        @Test
        void shouldReturnEmpty_whenListIsNotEmpty() {
            assertTrue(
                    validator.validateSchoolClassIds(List.of(1L, 2L))
                            .isEmpty()
            );
        }
    }
}
