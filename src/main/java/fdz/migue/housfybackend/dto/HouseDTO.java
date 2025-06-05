package fdz.migue.housfybackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseDTO {
    private Long houseId;

    @NotBlank(message = "House name cannot be blank")
    @Size(max = 255, message = "House name cannot exceed 255 characters")
    private String name;

    @Size(max = 500, message = "Address cannot exceed 500 characters")
    private String address;

    @Pattern(regexp = "^[0-9]{5}$", message = "Postal code must be 5 digits")
    private String postalCode;

    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String city;

    @DecimalMin(value = "0.0", inclusive = true, message = "Budget must be a positive value")
    @DecimalMax(value = "999999999.99", inclusive = true, message = "Budget too large")
    private BigDecimal budget;

    private Timestamp creationDate;
}