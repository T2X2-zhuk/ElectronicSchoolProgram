package SchoolStudent.validations.teacher;
import SchoolStudent.jpa.dto.SchoolClassDTO;
import SchoolStudent.jpa.dto.SpecificCodeForRegistrationInElectronicSchoolDTO;
import SchoolStudent.jpa.dto.TeacherDTO;
import SchoolStudent.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.controllers.GetLessonNameRestController;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.request.GetLessonNameRequest;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.response.GetLessonNameResponse;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolClassParameters;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForTeacherParameters;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class RegistrationTeacherValidatorTest {

    @Mock
    private ValidatorClassWithMethodsForTeacherParameters teacherValidator;
    @Mock
    private ValidatorClassWithMethodsForSchoolClassParameters schoolClassValidator;
    @Mock
    private ValidatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters codeValidator;
    @Mock
    private GetLessonNameRestController microserviceServiceGetLessonName;

    @InjectMocks
    private RegistrationTeacherValidator validator;

    private RegistrationTeacherRequest request;
    private TeacherDTO teacherDTO;
    private SchoolClassDTO schoolClassDTO;
    private ValidationError error;

    @BeforeEach
    void setUp() {
        schoolClassDTO = SchoolClassDTO.builder().number(5L).category("A").build();
        teacherDTO = TeacherDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .fatherland("Smith")
                .subject("Math")
                .email("test@example.com")
                .password("password123")
                .schoolClassDTO(schoolClassDTO)
                .build();

        request = new RegistrationTeacherRequest();
        request.setTeacherDTO(teacherDTO);
        request.setSpecificCodeForRegistrationInElectronicSchoolDTO(
                SpecificCodeForRegistrationInElectronicSchoolDTO.builder().specificCodeForRegistrationForTeacher("CODE123").build()
        );
        error = ValidationError.builder()
                .errorCode("ERROR_CODE")
                .description("Test error")
                .build();
    }

    @Test
    void shouldReturnErrorIfFirstNameEmpty() {
        teacherDTO.setFirstName(null);
        when(teacherValidator.firstNameMustNotBeEmpty(null)).thenReturn(Optional.of(error));
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.getFirst());
    }

    @Test
    void shouldReturnErrorIfEmailInvalid() {
        when(teacherValidator.mustNotBeEmptyEmail("test@example.com")).thenReturn(Optional.empty());
        when(teacherValidator.emailYourEmailIsNotCorrectError("test@example.com")).thenReturn(Optional.of(error));
        GetLessonNameResponse response = GetLessonNameResponse.builder()
                .lessonName("Math")
                .errors(List.of())
                .build();
        when(microserviceServiceGetLessonName.execute(any(GetLessonNameRequest.class)))
                .thenReturn(response);
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.get(0));
    }

    @Test
    void shouldReturnErrorIfSchoolClassNumberEmpty() {
        schoolClassDTO.setNumber(null);
        when(schoolClassValidator.numberMustNotBeEmpty(null)).thenReturn(Optional.of(error));
        GetLessonNameResponse response = GetLessonNameResponse.builder()
                .lessonName("Math")
                .errors(List.of())
                .build();
        when(microserviceServiceGetLessonName.execute(any(GetLessonNameRequest.class)))
                .thenReturn(response);
        List<ValidationError> result = validator.validate(request);
        assertEquals(1, result.size());
        assertEquals(error, result.get(0));
    }

    @Test
    void shouldReturnEmptyListWhenAllValidationsPass() {
        when(teacherValidator.firstNameMustNotBeEmpty("John")).thenReturn(Optional.empty());
        when(teacherValidator.lastNameMustNotBeEmpty("Doe")).thenReturn(Optional.empty());
        when(teacherValidator.fatherlandMustNotBeEmpty("Smith")).thenReturn(Optional.empty());
        when(teacherValidator.subjectMustNotBeEmpty("Math")).thenReturn(Optional.empty());
        when(teacherValidator.mustNotBeEmptyEmail("test@example.com")).thenReturn(Optional.empty());
        when(teacherValidator.emailYourEmailIsNotCorrectError("test@example.com")).thenReturn(Optional.empty());
        when(teacherValidator.suchEmailAlreadyExists("test@example.com")).thenReturn(Optional.empty());
        when(teacherValidator.mustNotBeEmptyPassword("password123")).thenReturn(Optional.empty());
        when(teacherValidator.suchPasswordAlreadyExists("password123")).thenReturn(Optional.empty());
        when(schoolClassValidator.numberMustNotBeEmpty(5L)).thenReturn(Optional.empty());
        when(schoolClassValidator.categoryMustNotBeEmpty("A")).thenReturn(Optional.empty());
        when(schoolClassValidator.fieldCategoryClassMustContainOneCapitalEnglishLetter("A")).thenReturn(Optional.empty());
        when(schoolClassValidator.suchSchoolClassIsNotExist(5L, "A")).thenReturn(Optional.empty());
        when(codeValidator.mustNotBeEmptySpecialCode("CODE123")).thenReturn(Optional.empty());
        when(codeValidator.validateSpecificCodeForTeacher("CODE123")).thenReturn(Optional.empty());
        GetLessonNameResponse response = GetLessonNameResponse.builder()
                .lessonName("Math")
                .errors(List.of())
                .build();
        when(microserviceServiceGetLessonName.execute(
                new GetLessonNameRequest("Math")
        )).thenReturn(response);
        List<ValidationError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }
}
