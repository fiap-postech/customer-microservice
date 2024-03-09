package br.com.fiap.tech.challenge.customer.driven.customer.inquiry.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class FileContentDTO {

    private String payload;

}
