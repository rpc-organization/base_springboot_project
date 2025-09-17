package jp.co.rpc.api.controller.v1.users.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー登録APIのレスポンス.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUsersResponse {

  @Schema(description = "登録したユーザーのID", requiredMode = Schema.RequiredMode.REQUIRED)
  private String userId;
}
