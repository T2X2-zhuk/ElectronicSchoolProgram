package SchoolStudent.services.schoolStudent;

import SchoolStudent.jpa.domain.SchoolClass;
import SchoolStudent.jpa.domain.SchoolStudent;
import SchoolStudent.jpa.dto.SchoolClassDTO;
import SchoolStudent.jpa.dto.SchoolStudentDTO;
import SchoolStudent.jpa.repositories.SchoolClassRepository;
import SchoolStudent.jpa.repositories.SchoolStudentRepository;
import SchoolStudent.request.student.GetStudentsBySchoolClassRequest;
import SchoolStudent.response.student.GetStudentsBySchoolClassResponse;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.student.GetStudentsBySchoolClassValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetStudentsBySchoolClassService {

    private final SchoolStudentRepository repository;
    private final SchoolClassRepository classRepository;
    private final GetStudentsBySchoolClassValidator validator;

    @Transactional
    public GetStudentsBySchoolClassResponse execute(GetStudentsBySchoolClassRequest request){
        List<ValidationError> validationErrors = validator.validationErrorDTOSList(request);
        if (!validationErrors.isEmpty()){
            return GetStudentsBySchoolClassResponse.builder().errors(validationErrors).build();
        }else {
            return getStudents(request);
        }
    }

    private GetStudentsBySchoolClassResponse getStudents(GetStudentsBySchoolClassRequest request){
        Optional<SchoolClass> schoolClassOpt =
                classRepository.findByNumberAndCategory(
                        request.getSchoolClassDTO().getNumber(),
                        request.getSchoolClassDTO().getCategory()
                );
        SchoolClass schoolClass = schoolClassOpt.orElseThrow();
        List<SchoolStudent> schoolStudents =
                repository.findAllBySchoolClass_Id(schoolClass.getId());

        List<SchoolStudentDTO> schoolStudentDTOS = schoolStudents.stream()
                .map(st -> SchoolStudentDTO.builder()
                        .id(st.getId())
                        .firstName(st.getFirstName())
                        .lastName(st.getLastName())
                        .email(st.getEmail())
                        .password(st.getPassword())
                        .schoolClassDTO(SchoolClassDTO.builder()
                                .id(st.getSchoolClass().getId())
                                .number(st.getSchoolClass().getNumber())
                                .category(st.getSchoolClass().getCategory()).build())
                        .build())
                .toList();
        return GetStudentsBySchoolClassResponse.builder()
                .schoolStudentDTOS(schoolStudentDTOS)
                .build();
    }
}
