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
import org.hibernate.validator.constraints.Range;

/**
 * ユーザー登録APIのリクエストパラメーター.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUsersRequest {

  @Schema(description = "登録するユーザーの姓", requiredMode = Schema.RequiredMode.REQUIRED)
  @Size(max = 256, message = MessageId.SIZE)
  @NotBlank(message = MessageId.NOT_BLANK)
  @NotNull(message = MessageId.NOT_NULL)
  private String firstName;

  @Schema(description = "登録するユーザーの名", requiredMode = Schema.RequiredMode.REQUIRED)
  @Size(max = 256, message = MessageId.SIZE)
  @NotBlank(message = MessageId.NOT_BLANK)
  @NotNull(message = MessageId.NOT_NULL)
  private String lastName;

  @Schema(description = "登録するユーザーの年齢", requiredMode = Schema.RequiredMode.REQUIRED)
  @Range(min = 0, max = 200, message = MessageId.RANGE)
  @NotNull(message = MessageId.NOT_NULL)
  private String age;
}
