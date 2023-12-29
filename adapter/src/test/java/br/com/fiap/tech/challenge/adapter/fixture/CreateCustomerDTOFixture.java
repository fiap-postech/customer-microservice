package br.com.fiap.tech.challenge.adapter.fixture;

import br.com.fiap.tech.challenge.application.dto.CreateCustomerDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCustomerDTOFixture {

    public static Model<CreateCustomerDTO> createCustomerDTOModel() {
        return Instancio.of(CreateCustomerDTO.class)
                .set(field(CreateCustomerDTO::getName), "Jose da Silva")
                .set(field(CreateCustomerDTO::getDocument), "19748826325")
                .set(field(CreateCustomerDTO::getEmail), "jose.silva@gmail.com")
                .toModel();
    }



}
