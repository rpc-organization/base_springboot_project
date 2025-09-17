package jp.co.rpc.api.controller.v1.users.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jp.co.rpc.api.constant.validatioin.MessageId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー情報取得APIのリクエストパラメーター.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersRequest {

  @Schema(description = "取得するユーザーのID", requiredMode = Schema.RequiredMode.REQUIRED)
  @Size(max = 64, message = MessageId.SIZE)
  @NotBlank(message = MessageId.NOT_BLANK)
  @NotNull(message = MessageId.NOT_NULL)
  private String userId;
}
