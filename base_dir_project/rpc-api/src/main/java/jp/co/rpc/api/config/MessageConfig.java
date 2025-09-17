package jp.co.rpc.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SuppressWarnings("unused")
@Configuration
@RequiredArgsConstructor
public class MessageConfig {

  private final MessageSource messageSource;

  @SuppressWarnings("unused")
  @Bean
  public LocalValidatorFactoryBean localValidatorFactoryBean() {
    LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
    localValidatorFactoryBean.setValidationMessageSource(messageSource);
    return localValidatorFactoryBean;
  }
}
