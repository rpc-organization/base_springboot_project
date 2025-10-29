package jp.co.rpc.api.util;

import java.util.Locale;
import java.util.ResourceBundle;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;

/**
 * 独自実装のメッセージを読み込むクラス.
 */
public class CustomResourceBundleLocator implements ResourceBundleLocator {

  /**
   * メッセージファイルのリソースパス.
   */
  private final String bundleName;

  /**
   * コンストラクタ.
   *
   * @param bundleName リソースパス.
   */
  public CustomResourceBundleLocator(String bundleName) {
    this.bundleName = bundleName;
  }

  /**
   * @param locale A locale, for which a resource bundle shall be retrieved. Must not be null.
   * @return バンドル.
   */
  @Override
  public ResourceBundle getResourceBundle(Locale locale) {
    return ResourceBundle.getBundle(bundleName, locale);
  }
}
