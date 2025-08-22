package springboot2.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnimePostRequestBody {
    @NotBlank(message = "The anime name cannot be null or empty")
    private String name;
}
