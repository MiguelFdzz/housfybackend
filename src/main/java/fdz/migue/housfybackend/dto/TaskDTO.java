package fdz.migue.housfybackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long taskId;
    @NotNull
    private Long houseId;
    @NotNull
    private Long categoryId;
    private Long productId;

    @NotBlank
    @Size(max = 255)
    private String title;
    private String description;
    @NotNull
    private LocalDate dueDate;
    private Boolean isCompleted;
    private Timestamp creationDate;
    private Timestamp completionDate;
}
