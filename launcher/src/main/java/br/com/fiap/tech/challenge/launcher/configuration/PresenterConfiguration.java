package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.adapter.presenter.DataRemovalPresenter;
import br.com.fiap.tech.challenge.adapter.presenter.PresenterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PresenterConfiguration {

    @Bean
    public CustomerPresenter customerPresenter() {
        return PresenterFactory.customerPresenter();
    }

    @Bean
    public DataRemovalPresenter dataRemovalPresenter() {
        return PresenterFactory.dataRemovalPresenter();
    }

}
