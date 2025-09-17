package jp.co.rpc.api.constant.openui;

public final class ContentExample {

  public static final class Error {


    public static final String E_001 = """
            {
                "code": "001",
                "message": "認証エラーが発生しました"
            }
        """;
    public static final String E_002 = """
            {
                "code": "002",
                "message": "バリデーションエラーが発生しました"
                "errorDetailList": {
                    {
                        "property": "userId",
                        "message": "必須です"
                    },
                    {
                        "property": "userId",
                        "message": "64文字以内にしてください"
                    }
                }
            }
        """;
    public static final String E_003 = """
            {
                "code": "003",
                "message": "リクエストヘッダー不正"
            }
        """;
    public static final String E_004 = """
            {
                "code": "004",
                "message": "リソースが見つかりませんでした"
            }
        """;
    public static final String E_005 = """
            {
                "code": "005",
                "message": "システムエラーが発生しました"
            }
        """;
    public static final String E_006 = """
            {
                "code": "006",
                "message": "DBエラーが発生しました"
            }
        """;

  }
}
