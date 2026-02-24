package SchoolStudent.services.schoolClass;

import SchoolStudent.jpa.domain.SchoolClass;
import SchoolStudent.jpa.dto.SchoolClassDTO;
import SchoolStudent.jpa.repositories.SchoolClassRepository;
import SchoolStudent.request.schoolClass.GetSchoolClassIdsRequest;
import SchoolStudent.response.schoolClass.GetSchoolClassIdsResponse;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.schoolClass.GetSchoolClassIdsValidator;
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
class GetSchoolClassIdsServiceTest {

    @Mock
    private SchoolClassRepository repository;
    @Mock
    private GetSchoolClassIdsValidator validator;

    @InjectMocks
    private GetSchoolClassIdsService service;

    private GetSchoolClassIdsRequest request;
    private SchoolClassDTO dto1;
    private SchoolClassDTO dto2;
    private ValidationError error;

    @BeforeEach
    void setUp() {
        dto1 = SchoolClassDTO.builder().number(5L).category("A").build();
        dto2 = SchoolClassDTO.builder().number(6L).category("B").build();
        request = new GetSchoolClassIdsRequest();
        request.setSchoolClassDTOS(List.of(dto1, dto2));
        error = ValidationError.builder()
                .errorCode("ERROR_CODE")
                .description("Test error")
                .build();
    }

    @Test
    void shouldReturnErrors_whenValidatorReturnsErrors() {
        when(repository.findByNumberAndCategory(5L, "A"))
                .thenReturn(Optional.of(SchoolClass.builder().id(1L).build()));
        when(repository.findByNumberAndCategory(6L, "B"))
                .thenReturn(Optional.of(SchoolClass.builder().id(2L).build()));
        when(validator.validate(List.of(1L, 2L))).thenReturn(List.of(error));
        GetSchoolClassIdsResponse response = service.execute(request);
        assertNotNull(response.getErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals(error, response.getErrors().get(0));
        assertNull(response.getStudentsIds());
    }

    @Test
    void shouldReturnStudentIds_whenValidationPasses() {
        when(repository.findByNumberAndCategory(5L, "A"))
                .thenReturn(Optional.of(SchoolClass.builder().id(1L).build()));
        when(repository.findByNumberAndCategory(6L, "B"))
                .thenReturn(Optional.of(SchoolClass.builder().id(2L).build()));
        when(validator.validate(List.of(1L, 2L))).thenReturn(List.of());
        GetSchoolClassIdsResponse response = service.execute(request);
        assertNotNull(response.getStudentsIds());
        assertEquals(2, response.getStudentsIds().size());
        assertTrue(response.getStudentsIds().containsAll(List.of(1L, 2L)));
        assertNull(response.getErrors());
    }

    @Test
    void shouldSkipNullIdsAndRemoveDuplicates() {
        SchoolClassDTO dtoDuplicate = SchoolClassDTO.builder().number(5L).category("A").build();
        request.setSchoolClassDTOS(List.of(dto1, dto2, dtoDuplicate));
        when(repository.findByNumberAndCategory(5L, "A"))
                .thenReturn(Optional.of(SchoolClass.builder().id(1L).build()));
        when(repository.findByNumberAndCategory(6L, "B"))
                .thenReturn(Optional.empty());
        when(validator.validate(List.of(1L))).thenReturn(List.of());
        GetSchoolClassIdsResponse response = service.execute(request);
        assertEquals(1, response.getStudentsIds().size());
        assertEquals(1L, response.getStudentsIds().get(0));
    }
}
