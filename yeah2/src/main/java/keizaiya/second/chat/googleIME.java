package keizaiya.second.chat;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class googleIME {
    private static final String GOOGLE_IME_URL =
            "https://www.google.com/transliterate?langpair=ja-Hira|ja&text=";

    public static String  convByGoogleIME(String org) {
        return conv(org);
    }

    // 変換の実行
    private static String conv(String org) {
        if ( org.length() == 0 ) {
            return "";
        }

        HttpURLConnection urlconn = null;
        BufferedReader reader = null;
        try {
            String baseurl;
            String encode = "UTF-8";
            baseurl = GOOGLE_IME_URL + URLEncoder.encode(org , "UTF-8");

            URL url = new URL(baseurl);

            urlconn = (HttpURLConnection)url.openConnection();
            urlconn.setRequestMethod("GET");
            urlconn.setInstanceFollowRedirects(false);
            urlconn.connect();

            reader = new BufferedReader(
                    new InputStreamReader(urlconn.getInputStream(), encode));

            String json = CharStreams.toString(reader);
            String parsed = parseJson(json);

            return parsed;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if ( urlconn != null ) {
                urlconn.disconnect();
            }
            if ( reader != null ) {
                try {
                    reader.close();
                } catch (IOException e) { // do nothing.
                }
            }
        }

        return "";
    }

    // デバッグ用エントリ
    /**
     * GoogleIMEの最初の変換候補を抽出して結合します
     *
     * @param json 変換元のJson形式の文字列
     * @return 変換後の文字列
     * @since 2.8.10
     */
    public static String parseJson(String json) {
        StringBuilder result = new StringBuilder();
        for (JsonElement response : new Gson().fromJson(json, JsonArray.class)) {
            result.append(response.getAsJsonArray().get(1).getAsJsonArray().get(0).getAsString());
        }
        return result.toString();
    }
}
