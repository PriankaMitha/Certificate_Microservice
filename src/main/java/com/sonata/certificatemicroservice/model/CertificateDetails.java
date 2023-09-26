package com.sonata.certificatemicroservice.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class CertificateDetails {
    @Id
    private ObjectId id;
    private Long certificateId;
    private String courseName;
    private Integer criticality;
    private String validity;
    private String vendor;
    private double cost;
    private Integer score;
}
