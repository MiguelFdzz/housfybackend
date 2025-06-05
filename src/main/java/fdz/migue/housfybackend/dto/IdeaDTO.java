package fdz.migue.housfybackend.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdeaDTO {
    private Long ideaId;

    @NotNull
    private Long houseId;

    @NotBlank
    @Size(max = 255)
    private String title;

    @Size(max = 1000)
    private String description;

    @NotNull
    private Long proposerUserId;

    @Size(max = 255)
    private String imageUrl;

    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal budget;

    private Timestamp creationDate;

    private Boolean isConvertedToPlan;
}