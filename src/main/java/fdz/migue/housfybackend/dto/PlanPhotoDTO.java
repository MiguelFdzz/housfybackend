package fdz.migue.housfybackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanPhotoDTO {
    private Long photoId;
    @NotNull
    private Long planId;
    @NotNull
    private Long uploadUserId;
    private String description;
    @NotNull
    @Size(min = 1)
    private String photoData;
    private Timestamp uploadDate;
}
