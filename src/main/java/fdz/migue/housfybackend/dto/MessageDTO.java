package fdz.migue.housfybackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long messageId;

    @NotNull
    private Long chatGroupId;

    @NotNull
    private Long senderUserId;

    @NotBlank
    @Size(max = 2000)
    private String textContent;

    private Timestamp creationDate;

    private Boolean isDeleted;
}