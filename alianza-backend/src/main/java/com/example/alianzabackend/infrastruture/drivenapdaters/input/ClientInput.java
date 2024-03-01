package com.example.alianzabackend.infrastruture.drivenapdaters.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ClientInput {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    private String phone;

    @NotNull(message = "La fecha de inicio no puede estar vacía")
    private LocalDate startDate;

    @NotNull(message = "La fecha de finalización no puede estar vacía")
    private LocalDate endDate;
}
