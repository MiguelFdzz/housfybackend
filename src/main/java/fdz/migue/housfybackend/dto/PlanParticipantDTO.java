package fdz.migue.housfybackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanParticipantDTO {
    @NotNull
    private Long planId;
    @NotNull
    private Long userId;

    private Timestamp participationDate;
}