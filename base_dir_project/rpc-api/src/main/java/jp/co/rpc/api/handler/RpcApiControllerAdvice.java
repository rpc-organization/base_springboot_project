package jp.co.rpc.api.handler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import jp.co.rpc.api.helper.MessageHelper;
import jp.co.rpc.api.model.response.RpcApiErrorResponse;
import jp.co.rpc.api.model.response.RpcApiErrorResponse.ErrorDetail;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 例外処理.
 */
@SuppressWarnings("unused")
@ControllerAdvice
@RequiredArgsConstructor
public class RpcApiControllerAdvice extends ResponseEntityExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final MessageHelper messageHelper;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      @NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status, @NonNull WebRequest request) {
    logger.error("バリデーションエラー", ex);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

    List<ErrorDetail> details = ex.getBindingResult().getFieldErrors().stream().map(
        error -> ErrorDetail.builder().property(error.getField())
            .message(messageHelper.getMassage(error)).build()).toList();

    RpcApiErrorResponse error = RpcApiErrorResponse.builder().Code("0001")
        .message("バリデーションエラー")
        .errorDetailList(details).build();

    return super.handleExceptionInternal(ex, error, httpHeaders, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleHandlerMethodValidationException(
      @NonNull HandlerMethodValidationException ex, @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status, @NonNull WebRequest request) {
    logger.error("バリデーションエラー", ex);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

    List<ErrorDetail> details = ex.getParameterValidationResults().stream().map(
        error -> ErrorDetail.builder()
            .property(StringUtils.defaultIfEmpty(
                Objects.requireNonNullElse(error.getArgument(), "").toString(),
                error.getMethodParameter().getParameter().getName()))
            .message(error.getResolvableErrors().stream().map(messageHelper::getMassage).collect(
                Collectors.joining())).build()).toList();

    RpcApiErrorResponse error = RpcApiErrorResponse.builder().Code("0001")
        .message("バリデーションエラー")
        .errorDetailList(details).build();

    return super.handleExceptionInternal(ex, error, httpHeaders, HttpStatus.BAD_REQUEST, request);
  }

  /**
   * 漏れが無いようすべてのエラーをハンドリング.
   *
   * @param e 例外.
   * @return 500エラー.
   */
  @SuppressWarnings("unused")
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAll(Exception e) {
    logger.error("システムエラー", e);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(HttpHeaders.CONTENT_TYPE,
            MediaType.APPLICATION_PROBLEM_JSON_VALUE)
        .body(RpcApiErrorResponse.builder().Code("005").message("システムエラーが発生しました"));
  }
}
