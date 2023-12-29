package br.com.fiap.tech.challenge.driven.mysql.fixture;

import br.com.fiap.tech.challenge.driven.mysql.model.CustomerEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.UUID;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerEntityFixture {

    public static Model<CustomerEntity> enabledCustomerEntityModel() {
        return Instancio.of(CustomerEntity.class)
                .set(field(CustomerEntity::getName), "Jose da Silva")
                .set(field(CustomerEntity::getEmail), "jose.silva@gmail")
                .set(field(CustomerEntity::getDocument), "19748826325")
                .set(field(CustomerEntity::isEnabled), true)
                .set(field(CustomerEntity::getUuid), UUID.randomUUID().toString())
                .toModel();
    }
}
