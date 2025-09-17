package jp.co.rpc.api.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SuppressWarnings("unused")
@Configuration
public class ApplicationConfig {

  @SuppressWarnings("unused")
  @Bean
  MessageSource messageSource() {
    ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
    source.setBasenames("classpath:i18n/messages");
    source.setCacheSeconds(0);
    source.setDefaultEncoding("UTF-8");
    return source;
  }
}
