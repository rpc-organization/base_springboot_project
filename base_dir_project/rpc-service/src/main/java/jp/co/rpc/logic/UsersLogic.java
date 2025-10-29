package jp.co.rpc.logic;

import jp.co.rpc.service.users.model.GetUsersResponseModel;
import jp.co.rpc.service.users.model.RegisterUsersRequestModel;
import org.springframework.stereotype.Service;

@Service
public class UsersLogic {

  /**
   * ユーザー情報取得処理.
   *
   * @param userId 取得するユーザーID.
   * @return ユーザー情報.
   */
  public GetUsersResponseModel getUsers(String userId) {
    // TODO repositoryを作成してDBから値を取得する処理追加
    return GetUsersResponseModel.builder().lastName("lastName").firstName("firstName").age(20)
        .build();
  }

  /**
   * ユーザー登録処理.
   *
   * @param requestModel 登録するユーザー情報.
   * @return 登録したユーザーID.
   */
  public String registerUsers(RegisterUsersRequestModel requestModel) {
    // TODO repositoryを作成してDBから値を登録する処理追加
    return "0001";
  }
}
