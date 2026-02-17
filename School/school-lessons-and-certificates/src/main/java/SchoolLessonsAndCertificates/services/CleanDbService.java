package SchoolLessonsAndCertificates.services;

import SchoolLessonsAndCertificates.jpa.database.CertificateRepository;
import SchoolLessonsAndCertificates.jpa.database.ClassLessonRepository;
import SchoolLessonsAndCertificates.jpa.database.LessonRepository;
import SchoolLessonsAndCertificates.request.CleanSchoolLessonsAndCertificatesDbRequest;
import SchoolLessonsAndCertificates.response.CleanSchoolLessonsAndCertificatesDbResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CleanDbService {

    private final CertificateRepository certificateRepository;
    private final ClassLessonRepository classLessonRepository;
    private final LessonRepository lessonRepository;

    @Transactional
    public CleanSchoolLessonsAndCertificatesDbResponse execute(CleanSchoolLessonsAndCertificatesDbRequest request) {
        log.info("{} is start!", this.getClass().getSimpleName());
        CleanSchoolLessonsAndCertificatesDbResponse response = new CleanSchoolLessonsAndCertificatesDbResponse();
        if (request.isCleanCertificateDb()) {
            certificateRepository.deleteAll();
            response.setDeleteCertificateDb(true);
            log.debug("Clean db Certificate");
        }
        if (request.isCleanClassLessonDb()) {
            classLessonRepository.deleteAll();
            response.setDeleteClassLessonDb(true);
            log.debug("Clean db ClassLesson");
        }
        if (request.isCleanLessonDb()) {
            lessonRepository.deleteAll();
            response.setDeleteLessonDb(true);
            log.debug("Clean db Lesson");
        }
        log.info("{} is execute!", this.getClass().getSimpleName());
        return response;
    }
}
