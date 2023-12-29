package br.com.fiap.tech.challenge.driven.mysql.service;

import br.com.fiap.tech.challenge.driven.mysql.mapping.DBCustomerMapper;
import br.com.fiap.tech.challenge.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.driven.mysql.repository.CustomerEntityRepository;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.tech.challenge.driven.mysql.fixture.CustomerDTOFixture.enabledCustomerDTOModel;
import static br.com.fiap.tech.challenge.driven.mysql.fixture.CustomerEntityFixture.enabledCustomerEntityModel;
import static br.com.fiap.tech.challenge.driven.mysql.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND_BY_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DBCustomerEntityRepositoryImplTest {

    @Mock
    private CustomerEntityRepository repository;

    private DBCustomerEntityRepositoryImpl service;

    @BeforeEach
    void setUp() {
        service = new DBCustomerEntityRepositoryImpl(repository, DBCustomerMapper.INSTANCE);
    }

    @Nested
    class ReadById {
        @Test
        void shouldReadWhenCustomerExists() {
            var customerEntity = create(enabledCustomerEntityModel());

            when(repository.findByUuid(any(String.class))).thenReturn(Optional.of(customerEntity));

            var response = service.readById(UUID.randomUUID().toString());

            assertThat(response).isNotNull();
            assertThat(response.getDocument()).isEqualTo(customerEntity.getDocument());
            assertThat(response.getName()).isEqualTo(customerEntity.getName());
            assertThat(response.getEmail()).isEqualTo(customerEntity.getEmail());
            assertThat(response.getId()).isEqualTo(customerEntity.getUuid());

            verify(repository).findByUuid(any(String.class));
        }

        @Test
        void shouldThrowExceptionWhenCustomerDoesNotExists() {
            var uuid = UUID.randomUUID().toString();

            when(repository.findByUuid(uuid)).thenReturn(Optional.empty());

            var exception = new ApplicationException(CUSTOMER_NOT_FOUND_BY_UUID, uuid);

            assertThatThrownBy(() -> service.readById(uuid))
                    .isInstanceOf(ApplicationException.class)
                    .hasMessage(exception.getMessage());

            verify(repository).findByUuid(uuid);
        }
    }

    @Nested
    class ReadByDocument {
        @Test
        void shouldReadWhenCustomerExists() {
            var customerEntity = create(enabledCustomerEntityModel());
            var document = customerEntity.getDocument();

            when(repository.findByDocumentAndEnabled(document, true)).thenReturn(Optional.of(customerEntity));

            var response = service.readByDocument(document);

            assertThat(response).isNotNull().isPresent();

            response.ifPresent(
                    dto -> {
                        assertThat(dto.getDocument()).isEqualTo(customerEntity.getDocument());
                        assertThat(dto.getName()).isEqualTo(customerEntity.getName());
                        assertThat(dto.getEmail()).isEqualTo(customerEntity.getEmail());
                        assertThat(dto.getId()).isEqualTo(customerEntity.getUuid());
                    }
            );

            verify(repository).findByDocumentAndEnabled(document, true);
        }

        @Test
        void shouldReturnEmptyWhenCustomerDoesNotExists() {
            var document = "12345678909";

            when(repository.findByDocumentAndEnabled(document, true)).thenReturn(Optional.empty());

            var response = service.readByDocument(document);

            assertThat(response)
                    .isNotNull()
                    .isNotPresent();

            verify(repository).findByDocumentAndEnabled(document, true);
        }
    }

    @Nested
    class Write {

        @Test
        void shouldInsertCustomer() {
            var customerDTO = create(enabledCustomerDTOModel());

            when(repository.existsByUuid(customerDTO.getId())).thenReturn(false);
            when(repository.save(any(CustomerEntity.class)))
                    .thenAnswer(i -> Arrays.stream(i.getArguments()).findFirst().orElseThrow());

            var response = service.write(customerDTO);

            assertThat(response)
                    .isNotNull()
                    .isEqualTo(customerDTO);

            verify(repository).existsByUuid(any(String.class));
            verify(repository).save(any(CustomerEntity.class));
            verify(repository, never()).findByUuid(any(String.class));
        }

        @Test
        void shouldUpdateCustomer() {
            var customerDTO = create(enabledCustomerDTOModel());
            var customerEntity = create(enabledCustomerEntityModel());

            customerEntity.setUuid(customerDTO.getId());

            when(repository.existsByUuid(customerDTO.getId())).thenReturn(true);
            when(repository.findByUuid(customerDTO.getId())).thenReturn(Optional.of(customerEntity));
            when(repository.save(any(CustomerEntity.class)))
                    .thenAnswer(i -> Arrays.stream(i.getArguments()).findFirst().orElseThrow());

            var response = service.write(customerDTO);

            assertThat(response)
                    .isNotNull()
                    .isEqualTo(customerDTO);

            verify(repository).existsByUuid(any(String.class));
            verify(repository).save(any(CustomerEntity.class));
            verify(repository).findByUuid(any(String.class));
        }

    }
}
