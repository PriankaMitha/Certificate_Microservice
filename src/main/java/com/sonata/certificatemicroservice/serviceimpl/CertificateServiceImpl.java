package com.sonata.certificatemicroservice.serviceimpl;

import com.sonata.certificatemicroservice.model.CertificateDetails;
import com.sonata.certificatemicroservice.repository.CertificateRepository;
import com.sonata.certificatemicroservice.service.CertificateService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;

    private static final Logger logger = LoggerFactory.getLogger(CertificateServiceImpl.class);
    @Override
    public List<CertificateDetails> getAllCourses() {
        try {
            List<CertificateDetails> list = certificateRepository.findAll();
            logger.info("Successfully retrieved {} certificate details.", list.size());
            return list;
        } catch (Exception e) {
            logger.error("Error occurred while retrieving certificate details: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve certificate details.", e);
        }
    }

    @Override
    public CertificateDetails getCourse(ObjectId certificateId) {
        return certificateRepository.findById(certificateId).get();
    }
    @Override
    public CertificateDetails getCourseByCertificateId(long certificateId) {
        try {
            CertificateDetails certificate = certificateRepository.findByCertificateId(certificateId);
            if (certificate != null) {
                logger.info("Certificate details retrieved successfully for ID: {}", certificateId);
            } else {
                logger.info("Certificate not found for ID: {}", certificateId);
            }
            return certificate;
        } catch (Exception e) {
            logger.error("Error occurred while retrieving certificate details for ID {}: {}", certificateId, e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve certificate details for ID " + certificateId, e);
        }
    }
    @Override
    public CertificateDetails saveCourse(CertificateDetails certificateDetails) {
        try {
            CertificateDetails savedCertificate = certificateRepository.save(certificateDetails);
            logger.info("Certificate details saved successfully: {}", savedCertificate);
            return savedCertificate;
        } catch (Exception e) {
            logger.error("Error occurred while saving certificate details: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save certificate details.", e);
        }
    }

    @Override
    public CertificateDetails deleteCourse(long certificateId) {
        try {
            CertificateDetails certificate = certificateRepository.findByCertificateId(certificateId);
            if (certificate != null) {
                certificateRepository.delete(certificate);
                logger.info("Certificate with ID {} deleted successfully.", certificateId);
            } else {
                logger.info("Certificate not found for deletion with ID: {}", certificateId);
            }
            return certificate;
        } catch (Exception e) {
            logger.error("Error occurred while deleting certificate with ID {}: {}", certificateId, e.getMessage(), e);
            throw new RuntimeException("Failed to delete certificate with ID " + certificateId, e);
        }
    }

    @Override
    public CertificateDetails updateCourse(CertificateDetails certificateDetails) {
        try {
            CertificateDetails existingDetails = certificateRepository.findByCertificateId(certificateDetails.getCertificateId());

            if (existingDetails != null) {
                if (certificateDetails.getCourseName() != null) {
                    existingDetails.setCourseName(certificateDetails.getCourseName());
                }
                if (certificateDetails.getCost() != 0) {
                    existingDetails.setCost(certificateDetails.getCost());
                }
                if (certificateDetails.getScore() != null) {
                    existingDetails.setScore(certificateDetails.getScore());
                }
                if (certificateDetails.getValidity() != null) {
                    existingDetails.setValidity(certificateDetails.getValidity());
                }
                if (certificateDetails.getVendor() != null) {
                    existingDetails.setVendor(certificateDetails.getVendor());
                }
                if (certificateDetails.getCriticality() != null) {
                    existingDetails.setCriticality(certificateDetails.getCriticality());
                }

                CertificateDetails updatedDetails = certificateRepository.save(existingDetails);
                logger.info("Certificate details updated successfully for ID: {}", certificateDetails.getCertificateId());
                return updatedDetails;
            } else {
                logger.info("Certificate not found for update with ID: {}", certificateDetails.getCertificateId());
                return null;
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating certificate details for ID {}: {}", certificateDetails.getCertificateId(), e.getMessage(), e);
            throw new RuntimeException("Failed to update certificate details for ID " + certificateDetails.getCertificateId(), e);
        }
    }
}
