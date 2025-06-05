package fdz.migue.housfybackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanCategoryDTO {
    private Long categoryId;
    @NotNull
    private Long houseId;
    @NotBlank
    private String name;
    private String icon;
}
