package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.adapter.presenter.DataRemovalPresenter;
import br.com.fiap.tech.challenge.application.usecase.customer.CreateCustomerUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByDocumentUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.FindDataRemovalByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.PublishDataRemovalUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.RemoveDataUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.RequestDataRemovalUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.UpdateDataRemovalUseCase;
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

    public static RequestDataRemovalController requestDataRemovalController(RequestDataRemovalUseCase removalUseCase, PublishDataRemovalUseCase publishUseCase, DataRemovalPresenter presenter) {
        return new RequestDataRemovalControllerImpl(removalUseCase, publishUseCase, presenter);
    }

    public static FindDataRemovalByUUIDController findDataRemovalByUUIDController(FindDataRemovalByUUIDUseCase useCase, DataRemovalPresenter presenter) {
        return new FindDataRemovalByUUIDControllerImpl(useCase, presenter);
    }

    public static RemoveDataController removeDataController(RemoveDataUseCase useCase) {
        return new RemoveDataControllerImpl(useCase);
    }

    public static UpdateDataRemovalController updateDataRemovalController(UpdateDataRemovalUseCase useCase) {
        return new UpdateDataRemovalControllerImpl(useCase);
    }
}

