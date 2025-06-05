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
public class ChatGroupDTO {
    private Long chatGroupId;

    @NotNull(message = "House ID is required")
    private Long houseId;

    @NotBlank(message = "Chat group name cannot be blank")
    @Size(max = 255, message = "Chat group name cannot exceed 255 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private Timestamp creationDate;
    private Timestamp lastMessage;
}