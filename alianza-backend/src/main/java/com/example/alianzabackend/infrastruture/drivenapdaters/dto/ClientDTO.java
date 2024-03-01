package com.example.alianzabackend.infrastruture.drivenapdaters.dto;
import com.example.alianzabackend.domain.model.Client;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class ClientDTO {
    private String sharedKey;
    private String name;
    private String email;
    private String phone;
    private String startDate;
    private String endDate;

    public static ClientDTO fromDomain(Client Client) {
        return new ClientDTO(
                Client.getSharedKey(),
                Client.getName(),
                Client.getEmail(),
                Client.getPhone(),
                Client.getStartDate().toString(),
                Client.getEndDate().toString()
        );
    }
}
