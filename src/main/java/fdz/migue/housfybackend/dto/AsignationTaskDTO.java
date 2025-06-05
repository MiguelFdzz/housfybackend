package fdz.migue.housfybackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignationTaskDTO {

    @NotNull(message = "Task ID is required")
    private Long taskId;

    @NotNull(message = "User ID is required")
    private Long userId;

    private Timestamp assignDate;
}