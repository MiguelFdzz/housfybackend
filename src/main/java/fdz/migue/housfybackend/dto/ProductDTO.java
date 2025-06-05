package fdz.migue.housfybackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    @NotNull
    private Long houseId;
    @NotBlank
    @Size(max = 255)
    private String name;
    private String description;
    private Boolean isBought;
}