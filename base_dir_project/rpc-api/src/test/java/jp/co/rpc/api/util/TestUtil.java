package jp.co.rpc.api.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

public class TestUtil {

  /**
   * バリデーションのテストで、実際のエラー文言を取得する設定.
   *
   * @return ValidatorFactory.
   */
  public ValidatorFactory getValidatorFactory() {
    return Validation.byDefaultProvider().configure()
        .messageInterpolator(new ResourceBundleMessageInterpolator(
            new CustomResourceBundleLocator("i18n.messages")))
        .buildValidatorFactory();
  }

  /**
   * 任意のリクエストオブジェクトを検証し、期待するメッセージのみが出ることを確認するメソッド.
   *
   * @param request                検証対象のオブジェクト
   * @param expectedViolationCount 期待する違反件数
   * @param expectedMessages       期待するエラーメッセージ（複数可）
   * @param <T>                    リクエスト型
   */
  public <T> void assertValidation(
      T request,
      int expectedViolationCount,
      String... expectedMessages
  ) {
    try (ValidatorFactory factory = getValidatorFactory()) {
      Validator validator = factory.getValidator();
      Set<ConstraintViolation<T>> violations = validator.validate(request);

      // 件数チェック
      assertEquals(expectedViolationCount, violations.size(), "違反件数が一致しません");

      // メッセージチェック
      assertThat(violations)
          .extracting(ConstraintViolation::getMessage)
          .containsOnly(expectedMessages);
    }
  }
}
