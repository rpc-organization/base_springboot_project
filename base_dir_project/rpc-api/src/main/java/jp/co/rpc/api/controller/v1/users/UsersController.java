package jp.co.rpc.api.controller.v1.users;

import static jp.co.rpc.api.constant.openui.ContentExample.Error.E_001;
import static jp.co.rpc.api.constant.openui.ContentExample.Error.E_002;
import static jp.co.rpc.api.constant.openui.ContentExample.Error.E_003;
import static jp.co.rpc.api.constant.openui.ContentExample.Error.E_004;
import static jp.co.rpc.api.constant.openui.ContentExample.Error.E_005;
import static jp.co.rpc.api.constant.openui.ContentExample.Error.E_006;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jp.co.rpc.api.controller.v1.users.payload.GetUsersRequest;
import jp.co.rpc.api.controller.v1.users.payload.GetUsersResponse;
import jp.co.rpc.api.controller.v1.users.payload.PostUsersRequest;
import jp.co.rpc.api.controller.v1.users.payload.PostUsersResponse;
import jp.co.rpc.service.users.UsersService;
import jp.co.rpc.service.users.model.GetUsersRequestModel;
import jp.co.rpc.service.users.model.GetUsersResponseModel;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ユーザー関連のAPIのコントローラークラス.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UsersController {

  private final UsersService usersService;

  /**
   * ユーザー情報取得API.
   *
   * @param request リクエストパラメーター.
   * @return 取得結果.
   */
  @GetMapping("")
  @Operation(summary = "ユーザー情報取得API", description = "指定したユーザーIDの情報を取得する")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "success",
              content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = GetUsersResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Bad Request",
              content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  examples = {
                      @ExampleObject(name = "002", description = "バリデーションエラー", value = E_002),
                      @ExampleObject(name = "003", description = "リクエストヘッダーエラー", value = E_003)
                  }
              )
          ),
          @ApiResponse(
              responseCode = "401",
              description = "Unauthorized",
              content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  examples = {
                      @ExampleObject(name = "001", description = "認証エラー", value = E_001),
                  }
              )
          ),
          @ApiResponse(
              responseCode = "403",
              description = "Not Found",
              content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  examples = {
                      @ExampleObject(name = "004", description = "リソース無しエラー", value = E_004),
                  }
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error",
              content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  examples = {
                      @ExampleObject(name = "005", description = "システムエラー", value = E_005),
                      @ExampleObject(name = "006", description = "DBエラー", value = E_006),
                  }
              )
          ),
      }
  )
  public GetUsersResponse getUsers(
      @ParameterObject @ModelAttribute @Validated GetUsersRequest request) {

    GetUsersResponseModel responseModel = usersService.getUsers(
        GetUsersRequestModel.builder().userId(request.getUserId()).build());

    return GetUsersResponse.builder().userId(request.getUserId())
        .firstName(responseModel.getFirstName()).lastName(responseModel.getLastName())
        .age(responseModel.getAge()).build();
  }

  /**
   * ユーザー登録API.
   *
   * @param request リクエストパラメーター.
   * @return 登録したユーザーのID.
   */
  @PostMapping("")
  @Operation(summary = "ユーザー登録API", description = "ユーザーの登録を行う")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "success",
              content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = PostUsersResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Bad Request",
              content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  examples = {
                      @ExampleObject(name = "002", description = "バリデーションエラー", value = E_002),
                      @ExampleObject(name = "003", description = "リクエストヘッダーエラー", value = E_003)
                  }
              )
          ),
          @ApiResponse(
              responseCode = "401",
              description = "Unauthorized",
              content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  examples = {
                      @ExampleObject(name = "001", description = "認証エラー", value = E_001),
                  }
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error",
              content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  examples = {
                      @ExampleObject(name = "005", description = "システムエラー", value = E_005),
                      @ExampleObject(name = "006", description = "DBエラー", value = E_006),
                  }
              )
          ),
      }
  )
  public PostUsersResponse postUsers(
      @RequestBody @Validated PostUsersRequest request) {

    return PostUsersResponse.builder().userId("999").build();
  }
}
