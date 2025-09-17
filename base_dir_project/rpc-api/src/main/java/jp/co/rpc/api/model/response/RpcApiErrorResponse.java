package jp.co.rpc.api.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcApiErrorResponse {

  @Schema(description = "エラーコード", example = "001", requiredMode = RequiredMode.REQUIRED)
  private String Code;

  @Schema(description = "エラーメッセージ", example = "システムエラーが発生しました", requiredMode = RequiredMode.REQUIRED)
  private String message;

  @Schema(description = "エラー詳細リスト", requiredMode = RequiredMode.NOT_REQUIRED)
  private List<ErrorDetail> errorDetailList;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ErrorDetail {

    @Schema(description = "エラーが発生した箇所", example = "userId", requiredMode = RequiredMode.NOT_REQUIRED)
    private String property;

    @Schema(description = "エラーの詳細", example = "必ず設定してください", requiredMode = RequiredMode.NOT_REQUIRED)
    private String message;
  }
}
