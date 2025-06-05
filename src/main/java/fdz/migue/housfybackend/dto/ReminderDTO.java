package fdz.migue.housfybackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReminderDTO {
    private Long reminderId;
    @NotNull
    private Long userId;
    @NotBlank
    private String title;
    private String description;
    @NotNull
    @FutureOrPresent
    private Timestamp reminderDate;
    private Boolean isCompleted;
}