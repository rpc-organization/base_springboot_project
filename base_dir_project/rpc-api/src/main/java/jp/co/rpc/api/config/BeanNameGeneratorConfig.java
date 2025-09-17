package jp.co.rpc.api.config;

import java.util.Optional;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * Bean名の重複を防ぐ設定.
 */
public class BeanNameGeneratorConfig extends AnnotationBeanNameGenerator {

  @Override
  protected String buildDefaultBeanName(BeanDefinition definition) {
    return Optional.ofNullable(definition.getBeanClassName()).orElse("");
  }
}
