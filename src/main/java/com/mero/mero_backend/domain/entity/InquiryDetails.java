package com.mero.mero_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "INQUIRY_DETAILS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDetails {
    @Id
    @Column(name = "INQUIRY_ID")
    private Long inquiryId;

    @Column(name = "COMPANY", nullable = false)
    private String company;

    @Column(name = "CONTACT_NUMBER", nullable = false)
    private String contactNumber;

    @Column(name = "RENTAL_START_DATE")
    private Date rentalStartDate;

    @Column(name = "RENTAL_END_DATE")
    private Date rentalEndDate;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "INTENDED_USE")
    private String intendedUse;

    @Column(name = "INQUIRY_DATE_TIME", insertable = false, updatable = false)
    private LocalDateTime inquiryDateTime;
}
