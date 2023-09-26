package com.sonata.certificatemicroservice.controller;

import com.sonata.certificatemicroservice.model.CertificateDetails;
import com.sonata.certificatemicroservice.model.CertificateDetailsList;
import com.sonata.certificatemicroservice.serviceimpl.CertificateServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CertificateController {

    @Autowired
    CertificateServiceImpl certificateServiceImpl;

    @Autowired
    CertificateDetailsList certificateDetailsList;

    @Operation(summary = "To get all the certificate data",
            description = "fetching all the available certificate details")
    @GetMapping("/certificate")
    public ResponseEntity<CertificateDetailsList> getAllCertificate(){
        certificateDetailsList.setCertificateList(certificateServiceImpl.getAllCourses());
        return ResponseEntity.ok(certificateDetailsList);
    }

    @GetMapping("/certificate/{certificateId}")
    public ResponseEntity<CertificateDetails> getCertificate(@PathVariable("certificateId") long id){
        //CertificateDetails certificateDetails = certificateServiceImpl.getCourseByCertificateId(id);
        return ResponseEntity.ok(certificateServiceImpl.getCourseByCertificateId(id));
    }

    @PostMapping("/SaveCertificate")
    public ResponseEntity<CertificateDetails> saveCertificate(@RequestBody CertificateDetails certificate) {
        CertificateDetails savedCertificate =certificateServiceImpl.saveCourse(certificate);
        return new ResponseEntity<>(savedCertificate, HttpStatus.CREATED);
    }

    @PutMapping("updateCertificate")
    public ResponseEntity<CertificateDetails> updateProduct(@RequestBody CertificateDetails certificate) {
        return ResponseEntity.ok(certificateServiceImpl.updateCourse(certificate));
    }
    
    @DeleteMapping("/certificate/{certificateId}")
    public ResponseEntity<CertificateDetails> deleteCertificate(@PathVariable("certificateId") long id) {
        CertificateDetails deletedCertificate =certificateServiceImpl.deleteCourse(id);
        return new ResponseEntity<>(deletedCertificate, HttpStatus.OK);
    }
}
