package jp.co.rpc.api.controller.v1.users;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import jp.co.rpc.api.controller.v1.users.payload.GetUsersRequest;
import jp.co.rpc.api.controller.v1.users.payload.GetUsersResponse;
import jp.co.rpc.api.controller.v1.users.payload.PostUsersRequest;
import jp.co.rpc.api.controller.v1.users.payload.PostUsersResponse;
import jp.co.rpc.api.util.TestUtil;
import jp.co.rpc.service.users.UsersService;
import jp.co.rpc.service.users.model.GetUsersRequestModel;
import jp.co.rpc.service.users.model.GetUsersResponseModel;
import jp.co.rpc.service.users.model.RegisterUsersRequestModel;
import jp.co.rpc.service.users.model.RegisterUsersResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * UsersControllerのテストクラス.
 */
@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

  private final TestUtil testUtil = new TestUtil();

  /**
   * モック注入対象.
   */
  @InjectMocks
  private UsersController controller;

  /**
   * UsersServiceモック化.
   */
  @Mock
  private UsersService service;

  @Nested
  @DisplayName("ユーザー情報取得APIのテスト")
  class GetUsers {

    @Test
    void 処理が正常終了する() {

      // モック設定.
      var getUsersRequestModel = GetUsersRequestModel.builder().userId("001").build();
      var getUsersResponseModel = GetUsersResponseModel.builder().userId("001").lastName("RPC")
          .firstName("太郎").age(20).build();
      when(service.getUsers(getUsersRequestModel)).thenReturn(getUsersResponseModel);

      // リクエスト.
      var getUsersRequest = GetUsersRequest.builder().userId("001").build();
      var actual = controller.getUsers(getUsersRequest);

      // 検証.
      var expected = GetUsersResponse.builder().userId("001").lastName("RPC")
          .firstName("太郎").age(20).build();
      assertEquals(expected, actual);
    }

    @Test
    void バリデーション_userIdがnull() {

      // リクエスト.
      var getUsersRequest = GetUsersRequest.builder().userId(null).build();

      // 検証.
      try (ValidatorFactory factory = testUtil.getValidatorFactory()) {
        var validator = factory.getValidator();
        Set<ConstraintViolation<GetUsersRequest>> violations = validator.validate(getUsersRequest);

        // 検証.
        assertEquals(2, violations.size());
        assertThat(violations).extracting("message")
            .containsOnly("空白は許可されていません", "Nullは許可されていません");
      }
    }

    @Test
    void バリデーション_userIdが空文字() {

      // リクエスト.
      var getUsersRequest = GetUsersRequest.builder().userId("").build();

      // 検証.
      try (ValidatorFactory factory = testUtil.getValidatorFactory()) {
        var validator = factory.getValidator();
        Set<ConstraintViolation<GetUsersRequest>> violations = validator.validate(getUsersRequest);

        // 検証.
        assertEquals(1, violations.size());
        assertThat(violations).extracting("message")
            .containsOnly("空白は許可されていません");
      }
    }

    @Test
    void バリデーション_userIdが65文字以上() {

      // リクエスト.
      var getUsersRequest = GetUsersRequest.builder().userId("a".repeat(65)).build();

      // 検証.
      try (ValidatorFactory factory = testUtil.getValidatorFactory()) {
        var validator = factory.getValidator();
        Set<ConstraintViolation<GetUsersRequest>> violations = validator.validate(getUsersRequest);

        // 検証.
        assertEquals(1, violations.size());
        assertThat(violations).extracting("message")
            .containsOnly("0以上、64以下に設定してください");
      }
    }
  }

  @Nested
  @DisplayName("ユーザー登録APIのテスト")
  class postUsers {

    @Test
    void 処理が正常終了する() {

      // モック設定.
      var registerUsersRequestModel = RegisterUsersRequestModel.builder().lastName("RPC")
          .firstName("太郎").age(20).build();
      var registerUsersResponseModel = RegisterUsersResponseModel.builder().userId("001").build();
      when(service.registerUsers(registerUsersRequestModel)).thenReturn(registerUsersResponseModel);

      // リクエスト.
      var postUsersRequest = PostUsersRequest.builder().lastName("RPC")
          .firstName("太郎").age(20).build();
      var actual = controller.postUsers(postUsersRequest);

      // 検証.
      var expected = PostUsersResponse.builder().userId("001").build();
      assertEquals(expected, actual);
    }

    @Test
    void バリデーション_リクエストパラメーターがNull() {

      testUtil.assertValidation(
          PostUsersRequest.builder().firstName(null).lastName("bbb").age(1).build(), 2,
          "Nullは許可されていません", "空白は許可されていません");

      testUtil.assertValidation(
          PostUsersRequest.builder().firstName("aaa").lastName(null).age(1).build(), 2,
          "Nullは許可されていません", "空白は許可されていません");

      testUtil.assertValidation(
          PostUsersRequest.builder().firstName("aaa").lastName("bbb").age(null).build(), 1,
          "Nullは許可されていません");
    }

    @Test
    void バリデーション_リクエストパラメーターが空文字() {

      testUtil.assertValidation(
          PostUsersRequest.builder().firstName("").lastName("bbb").age(1).build(), 1,
          "空白は許可されていません");

      testUtil.assertValidation(
          PostUsersRequest.builder().firstName("aaa").lastName("").age(1).build(), 1,
          "空白は許可されていません");
    }

    @Test
    void バリデーション_リクエストパラメーターが最大文字数超過() {

      testUtil.assertValidation(
          PostUsersRequest.builder().firstName("a".repeat(257)).lastName("bbb").age(1).build(), 1,
          "0以上、256以下に設定してください");

      testUtil.assertValidation(
          PostUsersRequest.builder().firstName("aaa").lastName("b".repeat(257)).age(1).build(), 1,
          "0以上、256以下に設定してください");
    }

    @Test
    void バリデーション_リクエストパラメーターの値が範囲外() {

      testUtil.assertValidation(
          PostUsersRequest.builder().firstName("aaa").lastName("bbb").age(-1).build(), 1,
          "0以上、200以下に設定してください");

      testUtil.assertValidation(
          PostUsersRequest.builder().firstName("aaa").lastName("bbb").age(201).build(), 1,
          "0以上、200以下に設定してください");
    }
  }
}
