package SchoolStudent.validations.student;

import SchoolStudent.request.student.RegistrationStudentRequest;
import SchoolStudent.validations.MethodsValidatorClasses.ValidationError;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolClassParameters;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSchoolStudentParameters;
import SchoolStudent.validations.MethodsValidatorClasses.ValidatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationStudentValidator {

    private final ValidatorClassWithMethodsForSchoolStudentParameters validatorClassWithMethodsForSchoolStudentParameters;
    private final ValidatorClassWithMethodsForSchoolClassParameters validatorClassWithMethodsForSchoolClassParameters;
    private final ValidatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters validatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters;

    public List<ValidationError> validate(RegistrationStudentRequest request) {
        List<ValidationError> errorDTOS = new ArrayList<>();
        validateFirstNameLastNameFatherland(errorDTOS, request);
        validateEmail(errorDTOS, request);
        validatePassword(errorDTOS, request);
        validateSpecificCode(errorDTOS, request);
        validateSchoolClassParameters(errorDTOS, request);
        return errorDTOS;
    }

    private void validateFirstNameLastNameFatherland(List<ValidationError> errorDTOS, RegistrationStudentRequest request) {
        validatorClassWithMethodsForSchoolStudentParameters.firstNameMustNotBeEmpty(request.getSchoolStudentDTO().getFirstName()).ifPresent(errorDTOS::add);
        validatorClassWithMethodsForSchoolStudentParameters.lastNameMustNotBeEmpty(request.getSchoolStudentDTO().getLastName()).ifPresent(errorDTOS::add);
        validatorClassWithMethodsForSchoolStudentParameters.fatherlandMustNotBeEmpty(request.getSchoolStudentDTO().getFatherland()).ifPresent(errorDTOS::add);
    }

    private void validateEmail(List<ValidationError> errorDTOS,RegistrationStudentRequest request) {
        validatorClassWithMethodsForSchoolStudentParameters.mustNotBeEmptyEmail(request.getSchoolStudentDTO().getEmail()).ifPresent(errorDTOS::add);
        if (errorDTOS.isEmpty()) {
            validatorClassWithMethodsForSchoolStudentParameters.emailYourEmailIsNotCorrectError(request.getSchoolStudentDTO().getEmail()).ifPresent(errorDTOS::add);
            if (errorDTOS.isEmpty()){
                validatorClassWithMethodsForSchoolStudentParameters.suchEmailAlreadyExists(request.getSchoolStudentDTO().getEmail()).ifPresent(errorDTOS::add);
            }
        }
    }

    private void validatePassword(List<ValidationError> errorDTOS,RegistrationStudentRequest request) {
        validatorClassWithMethodsForSchoolStudentParameters.mustNotBeEmptyPassword(request.getSchoolStudentDTO().getPassword()).ifPresent(errorDTOS::add);
        if (errorDTOS.isEmpty()) {
            validatorClassWithMethodsForSchoolStudentParameters.suchPasswordAlreadyExists(request.getSchoolStudentDTO().getPassword()).ifPresent(errorDTOS::add);
        }
    }

    private void validateSpecificCode(List<ValidationError> errorDTOS,RegistrationStudentRequest request) {
        validatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters.mustNotBeEmptySpecialCode(request.getSpecificCodeForRegistrationInElectronicSchoolDTO().getSpecificCodeForRegistrationForStudent()).ifPresent(errorDTOS::add);
        if (errorDTOS.isEmpty()) {
            validatorClassWithMethodsForSpecificCodeForRegistrationInElectronicSchoolParameters.validateSpecificCodeForStudent(request.getSpecificCodeForRegistrationInElectronicSchoolDTO().getSpecificCodeForRegistrationForStudent()).ifPresent(errorDTOS::add);
        }
    }

    private void validateSchoolClassParameters(List<ValidationError> errorDTOS,RegistrationStudentRequest request) {
        validatorClassWithMethodsForSchoolClassParameters.numberMustNotBeEmpty(request.getSchoolStudentDTO().getSchoolClassDTO().getNumber()).ifPresent(errorDTOS::add);
        validatorClassWithMethodsForSchoolClassParameters.categoryMustNotBeEmpty(request.getSchoolStudentDTO().getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
        if (errorDTOS.isEmpty()) {
            validatorClassWithMethodsForSchoolClassParameters.fieldCategoryClassMustContainOneCapitalEnglishLetter(request.getSchoolStudentDTO().getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
            if (errorDTOS.isEmpty()){
                validatorClassWithMethodsForSchoolClassParameters.suchSchoolClassIsNotExist(request.getSchoolStudentDTO().getSchoolClassDTO().getNumber(),request.getSchoolStudentDTO().getSchoolClassDTO().getCategory()).ifPresent(errorDTOS::add);
            }
        }
    }

}
