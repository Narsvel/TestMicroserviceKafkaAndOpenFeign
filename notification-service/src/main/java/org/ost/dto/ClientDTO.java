package org.ost.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private int id;

    @NotEmpty(message = "Name should not be empty.")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters.")
    private String name;

    @NotEmpty(message = "Email should not be empty.")
    @Email
    private String email;

    @Min(value = 0, message = "Age should be greater than 0.")
    private int age;
}
