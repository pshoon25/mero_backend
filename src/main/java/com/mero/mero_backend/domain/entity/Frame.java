package com.mero.mero_backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "FRAME")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Frame {
    @Id
    @Column(name = "FRAME_ID")
    private String frameId;
    
    @Column(name = "DESIGN_ID", nullable = false)
    private String designId;

    @Column(name = "FRAME_NAME", nullable = false)
    private String frameName;

    @Column(name = "FRAME_WIDTH")
    private Integer frameWidth;

    @Column(name = "FRAME_HEIGHT")
    private Integer frameHeight;

    @Column(name = "FRAME_CELL_CNT")
    private Integer frameCellCnt;

    @Column(name = "CELL_WIDTH")
    private Integer cellWidth;

    @Column(name = "CELL_HEIGHT")
    private Integer cellHeight;

    @Column(name = "BORDER_TOP")
    private Integer borderTop;

    @Column(name = "BORDER_BOTTOM")
    private Integer borderBottom;

    @Column(name = "BORDER_LEFT")
    private Integer borderLeft;

    @Column(name = "BORDER_RIGHT")
    private Integer borderRight;

    @Column(name = "CROSSBAR_HORIZONTAL")
    private Integer crossbarHorizontal;

    @Column(name = "CROSSBAR_VERTICAL")
    private Integer crossbarVertical;

    @Column(name = "USE_YN", nullable = false, columnDefinition = "CHAR(1) DEFAULT 'Y'")
    private String useYn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DESIGN_ID", referencedColumnName = "DESIGN_ID", insertable = false, updatable = false)
    private DesignManagement designManagement;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FRAME_ID", referencedColumnName = "FRAME_ID", insertable = false, updatable = false) // ShootSettings의 외래 키가 Frame의 FRAME_ID를 참조한다고 가정
    private ShootSettings shootSettings;
}
