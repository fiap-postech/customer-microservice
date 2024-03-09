package br.com.fiap.tech.challenge.adapter.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class DataRemovalInquiryDTO {

    private String dataRemovalId;
    private String customerId;
    private String name;
    private String email;
    private String document;
    private LocalDateTime removalRequested;

}
