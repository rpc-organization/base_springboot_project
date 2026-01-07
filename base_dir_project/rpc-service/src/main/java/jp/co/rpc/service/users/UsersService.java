package jp.co.rpc.service.users;

import jp.co.rpc.logic.UsersLogic;
import jp.co.rpc.service.users.model.GetUsersRequestModel;
import jp.co.rpc.service.users.model.GetUsersResponseModel;
import jp.co.rpc.service.users.model.RegisterUsersRequestModel;
import jp.co.rpc.service.users.model.RegisterUsersResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

  private final UsersLogic usersLogic;

  /**
   * ユーザー情報取得サービス.
   *
   * @param requestModel 取得するユーザー.
   * @return ユーザー情報.
   */
  public GetUsersResponseModel getUsers(GetUsersRequestModel requestModel) {
    return usersLogic.getUsers(requestModel.getUserId());
  }

  /**
   * ユーザー登録サービス.
   *
   * @param requestModel 登録するユーザー.
   * @return 登録したユーザーの情報.
   */
  public RegisterUsersResponseModel registerUsers(RegisterUsersRequestModel requestModel) {

    if (requestModel.getAge() < 0) {
      throw new NumberFormatException("年齢は正の値のみ");
    }

    String registeredUserID = usersLogic.registerUsers(requestModel);

    return RegisterUsersResponseModel.builder().userId(registeredUserID).build();
  }
}
