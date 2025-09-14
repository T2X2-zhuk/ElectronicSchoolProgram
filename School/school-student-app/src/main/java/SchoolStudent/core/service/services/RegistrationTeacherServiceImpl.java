package SchoolStudent.core.service.services;

import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceResponse;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForGetSubjectNameService;
import SchoolStudent.core.database.SchoolClassRepository;
import SchoolStudent.core.database.TeacherRepository;
import SchoolStudent.core.domain.SchoolClass;
import SchoolStudent.core.domain.Teacher;
import SchoolStudent.core.request.student.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.core.response.student.RegistrationStudentInDatabaseResponse;
import SchoolStudent.core.response.teacher.RegistrationTeacherResponse;
import SchoolStudent.core.service.interfeicesForServices.RegistrationStudentInDatabaseService;
import SchoolStudent.core.service.interfeicesForServices.RegistrationTeacherService;
import SchoolStudent.core.validations.student.RegistrationStudentValidator;
import SchoolStudent.core.validations.teacher.RegistrationTeacherValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationTeacherServiceImpl implements RegistrationTeacherService {

    @Autowired private RegistrationTeacherValidator validator;
    @Autowired private SchoolLessonsAndCertificatesMicroserviceForGetSubjectNameService microservice;
    @Autowired private TeacherRepository repository;
    @Autowired private SchoolClassRepository schoolClassRepository;
    @Override
    public RegistrationTeacherResponse execute(RegistrationTeacherRequest request) {
        RegistrationTeacherResponse response = new RegistrationTeacherResponse();
        if (!validator.validate(request).isEmpty()){
           response.setErrors(validator.validate(request));
        }else {
            GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceResponse microserviceResponse = buildMicroserviceResponse(request);
            if (microserviceResponse.hasErrors()){
                response.setErrors(microserviceResponse.getErrors());
            }else {
                repository.save(buildTeacher(request, microserviceResponse.getLessonName()));
                response.setMessage("You registered in database");
            }
        }
        return response;
    }


    private GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceResponse buildMicroserviceResponse(RegistrationTeacherRequest request){
        GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceRequest microserviceRequest =
                new GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceRequest(request.getSubject());
        return microservice.execute(microserviceRequest);
    }
    private Teacher buildTeacher(RegistrationTeacherRequest request , String lessonName){
        SchoolClass schoolClass = schoolClassRepository.findBynumberAndcategory(request.getNumber(),request.getCategory());
        Teacher teacher = new Teacher();
        teacher.setFirst_name(request.getFirst_name());
        teacher.setLast_name(request.getLast_name());
        teacher.setFatherland(request.getFatherland());
        teacher.setEmail(request.getEmail());
        teacher.setPassword(request.getPassword());
        teacher.setSubject(lessonName);
        teacher.setClasses_id(schoolClass);
        return teacher;
    }


}
