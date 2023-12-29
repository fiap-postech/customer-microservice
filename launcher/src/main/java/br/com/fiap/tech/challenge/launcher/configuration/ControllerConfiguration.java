package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.controller.customer.CreateCustomerController;
import br.com.fiap.tech.challenge.adapter.controller.customer.CustomerControllerFactory;
import br.com.fiap.tech.challenge.adapter.controller.customer.FindCustomerByDocumentController;
import br.com.fiap.tech.challenge.adapter.controller.customer.FindCustomerByUUIDController;
import br.com.fiap.tech.challenge.adapter.controller.customer.UpgradeCustomerController;
import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.application.usecase.customer.CreateCustomerUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByDocumentUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.UpgradeCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public CreateCustomerController createCustomerController(CreateCustomerUseCase useCase, CustomerPresenter presenter) {
        return CustomerControllerFactory.createCustomerController(useCase, presenter);
    }

    @Bean
    public FindCustomerByDocumentController findCustomerByDocumentController(FindCustomerByDocumentUseCase useCase, CustomerPresenter presenter) {
        return CustomerControllerFactory.findCustomerByDocumentController(useCase, presenter);
    }

    @Bean
    public FindCustomerByUUIDController findCustomerByUUIDController(FindCustomerByUUIDUseCase useCase, CustomerPresenter presenter) {
        return CustomerControllerFactory.findCustomerByUUIDController(useCase, presenter);
    }

    @Bean
    public UpgradeCustomerController upgradeCustomerController(UpgradeCustomerUseCase useCase, CustomerPresenter presenter) {
        return CustomerControllerFactory.upgradeCustomerController(useCase, presenter);
    }
}
