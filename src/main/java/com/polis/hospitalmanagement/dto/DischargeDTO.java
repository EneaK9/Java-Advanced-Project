package com.polis.hospitalmanagement.dto;

import com.polis.hospitalmanagement.entity.DischargeReason;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO class for transferring discharge data.
 * It simplifies data transfer by excluding unnecessary fields.
 */
@Getter
@Setter
public class DischargeDTO {
    private Long id; // Unique identifier for the discharge record
    private LocalDateTime dischargeDate;
    private String dischargeReason;
    private String notes;
    private Long patientId; // Reference to the patient

    /**
     * Converts the String discharge reason into an Enum.
     * @return DischargeReason Enum value corresponding to the stored String.
     */
    public DischargeReason getDischargeReasonEnum() {
        return DischargeReason.valueOf(this.dischargeReason);
    }

    /**
     * Constructor to initialize DischargeDTO fields.
     * @param id The ID of the discharge record.
     * @param dischargeDate The date and time when the discharge happened.
     * @param dischargeReason The reason for discharge (as a String).
     * @param notes Additional notes regarding the discharge.
     * @param patientId The ID of the patient associated with this discharge.
     */
    public DischargeDTO(Long id, LocalDateTime dischargeDate, String dischargeReason, String notes, Long patientId) {
        this.id = id;
        this.dischargeDate = dischargeDate;
        this.dischargeReason = dischargeReason; // Stored as a String for easier API communication
        this.notes = notes;
        this.patientId = patientId;
    }
    public Long getId() {
        return id;
    }

    public LocalDateTime getDischargeDate() {
        return dischargeDate;
    }

    public String getDischargeReason() {
        return dischargeReason;
    }

    public String getNotes() {
        return notes;
    }

    public Long getPatientId() {
        return patientId;
    }
}
