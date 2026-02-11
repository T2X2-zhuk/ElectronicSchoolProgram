package SchoolStudent.validations.teacher;

import SchoolStudent.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.controllers.LessonsAndCertificatesMicroserviceGetLessonName;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.request.GetLessonNameSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.response.GetLessonNameSchoolLessonsAndCertificatesMicroserviceResponse;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolClassParameters;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForTeacherParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationTeacherValidator {

    private final ValidatorClassWithMethodsForTeacherParameters validatorClassWithMethodsForTeacherParameters;
    private final ValidatorClassWithMethodsForSchoolClassParameters validatorClassWithMethodsForSchoolClassParameters;
    private final ValidatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters validatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters;
    private final LessonsAndCertificatesMicroserviceGetLessonName microserviceServiceGetLessonName;

    public List<ValidationError> validate(RegistrationTeacherRequest request) {
        List<ValidationError> errorDTOS = new ArrayList<>();
        validateFirstNameLastNameFatherlandSubject(errorDTOS, request);
        validateEmail(errorDTOS, request);
        validatePassword(errorDTOS, request);
        validateSpecificCode(errorDTOS, request);
        validateSchoolClassParameters(errorDTOS, request);
        return errorDTOS;
    }

    private void validateFirstNameLastNameFatherlandSubject(List<ValidationError> errorDTOS, RegistrationTeacherRequest request) {
        validatorClassWithMethodsForTeacherParameters.firstNameMustNotBeEmpty(request.getTeacherDTO().getFirstName()).ifPresent(errorDTOS::add);
        validatorClassWithMethodsForTeacherParameters.lastNameMustNotBeEmpty(request.getTeacherDTO().getLastName()).ifPresent(errorDTOS::add);
        validatorClassWithMethodsForTeacherParameters.fatherlandMustNotBeEmpty(request.getTeacherDTO().getFatherland()).ifPresent(errorDTOS::add);
        validatorClassWithMethodsForTeacherParameters.subjectMustNotBeEmpty(request.getTeacherDTO().getSubject()).ifPresent(errorDTOS::add);
        if (errorDTOS.isEmpty()){
            sendRequestToMicroservice(errorDTOS, request);
        }
    }

    private void sendRequestToMicroservice(List<ValidationError> errorDTOS, RegistrationTeacherRequest request) {
        GetLessonNameSchoolLessonsAndCertificatesMicroserviceResponse response =
                microserviceServiceGetLessonName.execute(GetLessonNameSchoolLessonsAndCertificatesMicroserviceRequest.builder()
                        .lessonName(request.getTeacherDTO().getSubject()).build());
        if (response.hasErrors()){
           errorDTOS.addAll(response.getErrors());
        }
        else {
            request.getTeacherDTO().setSubject(response.getLessonName());
        }
    }

    private void validateSchoolClassParameters(List<ValidationError> errorDTOS,RegistrationTeacherRequest request) {
        validatorClassWithMethodsForSchoolClassParameters.numberMustNotBeEmpty(request.getTeacherDTO().getSchoolClassDTO().getNumber()).ifPresent(errorDTOS::add);
        validatorClassWithMethodsForSchoolClassParameters.categoryMustNotBeEmpty(request.getTeacherDTO().getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
        if (errorDTOS.isEmpty()) {
            validatorClassWithMethodsForSchoolClassParameters.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getTeacherDTO().getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
            if (errorDTOS.isEmpty()){
                validatorClassWithMethodsForSchoolClassParameters.suchSchoolClassIsNotExist(request.getTeacherDTO().getSchoolClassDTO().getNumber(),request.getTeacherDTO().getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
            }
        }
    }

    private void validateSpecificCode(List<ValidationError> errorDTOS,RegistrationTeacherRequest request) {
        validatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters.mustNotBeEmptySpecialCode(request.getSpecificCodeForRegistrationInElectronicSchoolDTO().getSpecificCodeForRegistrationForTeacher()).ifPresent(errorDTOS::add);
        if (errorDTOS.isEmpty()) {
            validatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters.validateSpecificCodeForTeacher(request.getSpecificCodeForRegistrationInElectronicSchoolDTO().getSpecificCodeForRegistrationForTeacher()).ifPresent(errorDTOS::add);
        }
    }

    private void validateEmail(List<ValidationError> errorDTOS,RegistrationTeacherRequest request) {
        validatorClassWithMethodsForTeacherParameters.mustNotBeEmptyEmail(request.getTeacherDTO().getEmail()).ifPresent(errorDTOS::add);
        if (errorDTOS.isEmpty()) {
            validatorClassWithMethodsForTeacherParameters.emailYourEmailIsNotCorrectError(request.getTeacherDTO().getEmail()).ifPresent(errorDTOS::add);
            if (errorDTOS.isEmpty()){
                validatorClassWithMethodsForTeacherParameters.suchEmailAlreadyExists(request.getTeacherDTO().getEmail()).ifPresent(errorDTOS::add);
            }
        }
    }

    private void validatePassword(List<ValidationError> errorDTOS,RegistrationTeacherRequest request) {
        validatorClassWithMethodsForTeacherParameters.mustNotBeEmptyPassword(request.getTeacherDTO().getPassword()).ifPresent(errorDTOS::add);
        if (errorDTOS.isEmpty()) {
            validatorClassWithMethodsForTeacherParameters.suchPasswordAlreadyExists(request.getTeacherDTO().getPassword()).ifPresent(errorDTOS::add);
        }
    }
}
