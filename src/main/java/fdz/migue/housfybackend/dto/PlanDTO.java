package fdz.migue.housfybackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import fdz.migue.housfybackend.enums.PlanStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO {
    private Long planId;
    private Long ideaId;
    @NotBlank
    private String title;
    private String description;
    private Long categoryId;
    private BigDecimal budget;
    private List<LocalDate> days;
    private String suggestedPlace;
    @NotNull
    private Long proposerUserId;
    private Timestamp creationDate;
    private Timestamp updateDate;
    @NotNull
    private PlanStatus status;
    private String materiales;
    @NotNull
    private Long houseId;
}