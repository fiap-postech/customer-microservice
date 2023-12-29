package br.com.fiap.tech.challenge.driven.mysql.fixture;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.driven.mysql.model.CustomerEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.UUID;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDTOFixture {

    public static Model<CustomerDTO> enabledCustomerDTOModel() {
        return Instancio.of(CustomerDTO.class)
                .set(field(CustomerDTO::getName), "Jose da Silva")
                .set(field(CustomerDTO::getEmail), "jose.silva@gmail")
                .set(field(CustomerDTO::getDocument), "19748826325")
                .set(field(CustomerDTO::isEnabled), true)
                .set(field(CustomerDTO::getId), UUID.randomUUID().toString())
                .toModel();
    }
}
