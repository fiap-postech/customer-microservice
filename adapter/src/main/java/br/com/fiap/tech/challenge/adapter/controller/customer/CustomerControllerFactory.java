package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.application.usecase.customer.CreateCustomerUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByDocumentUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.UpgradeCustomerUseCase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerControllerFactory {

    public static CreateCustomerController createCustomerController(CreateCustomerUseCase useCase, CustomerPresenter presenter) {
        return new CreateCustomerControllerImpl(useCase, presenter);
    }

    public static FindCustomerByDocumentController findCustomerByDocumentController(FindCustomerByDocumentUseCase useCase, CustomerPresenter presenter) {
        return new FindCustomerByDocumentControllerImpl(useCase, presenter);
    }

    public static FindCustomerByUUIDController findCustomerByUUIDController(FindCustomerByUUIDUseCase useCase, CustomerPresenter presenter) {
        return new FindCustomerByUUIDControllerImpl(useCase, presenter);
    }

    public static UpgradeCustomerController upgradeCustomerController(UpgradeCustomerUseCase useCase, CustomerPresenter presenter){
        return new UpgradeCustomerControllerImpl(useCase, presenter);
    }
}
