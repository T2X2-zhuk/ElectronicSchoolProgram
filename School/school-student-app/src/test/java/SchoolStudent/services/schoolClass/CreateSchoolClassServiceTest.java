package SchoolStudent.services.schoolClass;
import SchoolStudent.jpa.domain.SchoolClass;
import SchoolStudent.jpa.dto.SchoolClassDTO;
import SchoolStudent.jpa.repositories.SchoolClassRepository;
import SchoolStudent.request.schoolClass.CreateSchoolClassRequest;
import SchoolStudent.response.schoolClass.CreateSchoolClassResponse;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.schoolClass.CreateSchoolClassValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CreateSchoolClassServiceTest {

    @Mock
    private CreateSchoolClassValidator validator;
    @Mock
    private SchoolClassRepository repository;

    @InjectMocks
    private CreateSchoolClassService service;

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

    @Test
    void shouldReturnErrors_whenValidationFails() {
        when(validator.validate(request)).thenReturn(List.of(error));
        CreateSchoolClassResponse response = service.execute(request);
        assertNotNull(response.getErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals(error, response.getErrors().get(0));
        verify(repository, never()).save(any());
    }

    @Test
    void shouldSaveSchoolClass_whenValidationPasses() {
        when(validator.validate(request)).thenReturn(List.of());
        CreateSchoolClassResponse response = service.execute(request);
        ArgumentCaptor<SchoolClass> captor = ArgumentCaptor.forClass(SchoolClass.class);
        verify(repository).save(captor.capture());
        SchoolClass savedClass = captor.getValue();
        assertEquals(schoolClassDTO.getNumber(), savedClass.getNumber());
        assertEquals(schoolClassDTO.getCategory(), savedClass.getCategory());
        assertEquals("School class successful created.", response.getMessage());
        assertNull(response.getErrors());
    }
}
