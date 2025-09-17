package jp.co.rpc.api.helper;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageHelper {

  private final MessageSource messageSource;

  public String getMassage(String code) {
    return messageSource.getMessage(code, null, Locale.JAPAN);
  }

  public String getMassage(String code, Object[] args) {
    return messageSource.getMessage(code, args, Locale.JAPAN);
  }

  public String getMassage(MessageSourceResolvable resolvable) {
    return messageSource.getMessage(resolvable, Locale.JAPAN);
  }
}
