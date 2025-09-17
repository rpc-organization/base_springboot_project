package jp.co.rpc;

import jp.co.rpc.api.config.BeanNameGeneratorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {
    "jp.co.rpc.api",
    "jp.co.rpc.service",
    "jp.co.rpc.logic"
}, nameGenerator = BeanNameGeneratorConfig.class)
public class BaseDirProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(BaseDirProjectApplication.class, args);
  }

}
