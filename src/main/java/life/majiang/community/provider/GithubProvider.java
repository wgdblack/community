package life.majiang.community.provider;
//这个包就是借用别人的代码

import life.majiang.community.dto.AccessTokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;
import sun.awt.geom.AreaOp;

import java.io.IOException;

@Component//当前类初始化到spring容器的上下文
//如果参数超过两个，就要封装成对象
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
         MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                System.out.println(string);//快捷键sout
                return response.body().string();
            } catch (IOException e) {

            }
            return null;
    }
}
