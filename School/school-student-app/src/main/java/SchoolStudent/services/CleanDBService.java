package SchoolStudent.services;

import SchoolStudent.jpa.repositories.CodeForRegistrationRepository;
import SchoolStudent.jpa.repositories.SchoolClassRepository;
import SchoolStudent.jpa.repositories.SchoolStudentRepository;
import SchoolStudent.jpa.repositories.TeacherRepository;
import SchoolStudent.request.CleanSchoolStudentDbRequest;
import SchoolStudent.response.CleanSchoolStudentDbResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CleanDBService {

    private final SchoolStudentRepository schoolStudentRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final CodeForRegistrationRepository codeForRegistrationRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
    public CleanSchoolStudentDbResponse execute(CleanSchoolStudentDbRequest request) {
        log.info("{} is start!", this.getClass().getSimpleName());
        CleanSchoolStudentDbResponse response = new CleanSchoolStudentDbResponse();
        if (request.isCleanSchoolStudent()) {
            schoolStudentRepository.deleteAll();
            response.setDeleteSchoolStudent(true);
            log.debug("Clean db School Student");
        }
        if (request.isCleanTeacher()) {
            teacherRepository.deleteAll();
            response.setDeleteTeacher(true);
            log.debug("Clean db Teacher");
        }
        if (request.isCleanSpecificCodeForRegistrationInElectronicSchool()) {
            codeForRegistrationRepository.deleteAll();
            response.setDeleteSpecificCodeForRegistrationInElectronicSchool(true);
            log.debug("Clean db Specific Code For Registration In Electronic School");
        }
        if (request.isCleanSchoolClass()) {
            schoolClassRepository.deleteAll();
            response.setDeleteSchoolClass(true);
            log.debug("Clean db School Class");
        }
        log.info("{} is execute!", this.getClass().getSimpleName());
        return response;
    }
}
