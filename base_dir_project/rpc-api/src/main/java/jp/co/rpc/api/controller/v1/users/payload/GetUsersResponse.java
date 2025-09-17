package jp.co.rpc.api.controller.v1.users.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー情報取得APIのレスポンス.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponse {

  @Schema(description = "取得したユーザーのID", requiredMode = Schema.RequiredMode.REQUIRED)
  private String userId;

  @Schema(description = "取得したユーザーの姓", requiredMode = Schema.RequiredMode.REQUIRED)
  private String firstName;

  @Schema(description = "取得したユーザーの名", requiredMode = Schema.RequiredMode.REQUIRED)
  private String lastName;

  @Schema(description = "取得したユーザーの年齢", requiredMode = Schema.RequiredMode.REQUIRED)
  private Integer age;
}
