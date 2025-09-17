package jp.co.rpc.logic;

import jp.co.rpc.service.users.model.GetUsersResponseModel;
import org.springframework.stereotype.Service;

@Service
public class UsersLogic {

  public GetUsersResponseModel getUsers(String userId) {
    return GetUsersResponseModel.builder().lastName("lastName").firstName("firstName").age(20)
        .build();
  }
}
