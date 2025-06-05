package fdz.migue.housfybackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdeaVoteDTO {
    @NotNull
    private Long ideaId;
    @NotNull
    private Long userId;

    @NotNull
    @Min(value = -1)
    @Max(value = 1)
    private Short voteType;

    private Timestamp creationDate;
}