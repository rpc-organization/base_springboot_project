package jp.co.rpc.service.users;

import jp.co.rpc.logic.UsersLogic;
import jp.co.rpc.service.users.model.GetUsersRequestModel;
import jp.co.rpc.service.users.model.GetUsersResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

  private final UsersLogic usersLogic;

  public GetUsersResponseModel getUsers(GetUsersRequestModel requestModel) {
    return usersLogic.getUsers(requestModel.getUserId());
  }
}
