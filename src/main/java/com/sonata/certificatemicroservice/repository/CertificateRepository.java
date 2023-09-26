package com.sonata.certificatemicroservice.repository;

import com.sonata.certificatemicroservice.model.CertificateDetails;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface CertificateRepository extends MongoRepository<CertificateDetails, ObjectId> {

    CertificateDetails findByCertificateId(long certificateId);


}
