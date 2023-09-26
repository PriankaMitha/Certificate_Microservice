package com.sonata.certificatemicroservice.service;

import com.sonata.certificatemicroservice.model.CertificateDetails;
import com.sonata.certificatemicroservice.repository.CertificateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CertificateService {

    public List<CertificateDetails> getAllCourses();
    public CertificateDetails getCourse(ObjectId certificateId);
    public CertificateDetails getCourseByCertificateId(long certificateId);
    public CertificateDetails saveCourse(CertificateDetails certificateDetails);
    public CertificateDetails deleteCourse(long certificateId);
    public CertificateDetails updateCourse(CertificateDetails certificateDetails);

}
