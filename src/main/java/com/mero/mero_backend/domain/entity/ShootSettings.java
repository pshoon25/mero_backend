package com.mero.mero_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SHOOT_SETTINGS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShootSettings {
    @Id
    @Column(name = "SETTING_ID")
    private String settingId;

    @Column(name = "COMPANY_ID", nullable = false)
    private String companyId;

    @Column(name = "FRAME_ID", nullable = false)
    private String frameId;

    @Column(name = "COUNTDOWN_TIME", nullable = false)
    private String countdownTime;
  
    @Column(name = "SHOT_COUNT", nullable = false)
    private String shotCount;
  
    @Column(name = "PRINT_COUNT", nullable = false)
    private String printCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID", insertable = false, updatable = false)
    private CompanyInfo companyInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FRAME_ID", referencedColumnName = "FRAME_ID", insertable = false, updatable = false)
    private Frame frame;
}
