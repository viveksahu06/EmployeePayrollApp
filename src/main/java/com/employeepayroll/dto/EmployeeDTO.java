package com.employeepayroll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotEmpty(message = "Name is required and cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]+$", message = "Name must start with an uppercase letter and should not contain special characters")
    private String name;

    @Min(value = 500 , message = "Minimum should be more than 500")
    private double salary;

    @NotNull(message = "Department should not be empty")
    private List<String> department;

    @NotEmpty(message = "Gender is required and cannot be empty")
    @Pattern(regexp = "^(Male|Female)$", message = "Gender can be either Male or Female")
    private String gender;

    @JsonFormat(pattern = "dd MMM yyyy")
    @PastOrPresent(message = "Start date must be a past or present date")
    @NotNull(message = "This field is required")
    private LocalDate startDate;

    @NotBlank(message = "This field is required")
    private String note;

    @NotBlank(message = "This field is required")
    private String profilePic;
}
