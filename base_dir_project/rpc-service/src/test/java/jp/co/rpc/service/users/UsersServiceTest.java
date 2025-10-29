package jp.co.rpc.service.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import jp.co.rpc.logic.UsersLogic;
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
 * UsersServiceのテストクラス.
 */
@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

  /**
   * テスト対象サービスクラス
   */
  @InjectMocks
  private UsersService service;

  /**
   * ロジッククラス
   */
  @Mock
  private UsersLogic usersLogic;

  @Nested
  @DisplayName("ユーザー情報取得APIのテスト")
  class GetUsers {

    @Test
    void 処理が正常終了する() {

      // モック設定.
      var getUsersResponseModel = GetUsersResponseModel.builder().userId("001").lastName("RPC")
          .firstName("太郎").age(20).build();
      when(usersLogic.getUsers("0001")).thenReturn(getUsersResponseModel);

      // リクエスト.
      var getUsersRequestModel = GetUsersRequestModel.builder().userId("0001").build();
      var actual = service.getUsers(getUsersRequestModel);

      // 検証.
      var expected = GetUsersResponseModel.builder().userId("001").lastName("RPC")
          .firstName("太郎").age(20).build();
      assertEquals(expected, actual);
    }
  }

  @Nested
  @DisplayName("ユーザー登録APIのテスト")
  class registerUsers {

    @Test
    void 処理が正常終了する() {

      // モック設定.
      var registerUsersRequestModelLogic = RegisterUsersRequestModel.builder().lastName("RPC")
          .firstName("太郎").age(20).build();
      when(usersLogic.registerUsers(registerUsersRequestModelLogic)).thenReturn("0001");

      // リクエスト.
      var registerUsersRequestModelService = RegisterUsersRequestModel.builder().lastName("RPC")
          .firstName("太郎").age(20).build();
      var actual = service.registerUsers(registerUsersRequestModelService);

      // 検証.
      var expected = RegisterUsersResponseModel.builder().userId("0001").build();
      assertEquals(expected, actual);
    }
  }
}
