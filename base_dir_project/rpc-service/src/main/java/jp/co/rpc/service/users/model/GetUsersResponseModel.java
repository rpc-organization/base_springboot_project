package jp.co.rpc.service.users.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponseModel {

  private String userId;

  private String firstName;

  private String lastName;

  private Integer age;
}
