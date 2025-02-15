package com.polis.hospitalmanagement.dto;

import com.polis.hospitalmanagement.entity.DischargeReason;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DischargeDTO {
    private Long id;
    private LocalDateTime dischargeDate;
    private String dischargeReason;
    private String notes;
    private Long patientId; // Reference to the patient

    public DischargeReason getDischargeReasonEnum() {
        return DischargeReason.valueOf(this.dischargeReason);
    }

    public DischargeDTO(Long id, LocalDateTime dischargeDate, String dischargeReason, String notes, Long patientId) {
        this.id = id;
        this.dischargeDate = dischargeDate;
        this.dischargeReason = dischargeReason;
        this.notes = notes;
        this.patientId = patientId;
    }
}
