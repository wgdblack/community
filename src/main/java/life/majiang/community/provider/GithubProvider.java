package life.majiang.community.provider;
//这个包就是借用别人的代码

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import sun.awt.geom.AreaOp;

import java.io.IOException;

@Component//当前类初始化到spring容器的上下文
//如果参数超过两个，就要封装成对象
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String token = string.split("&")[0].split("=")[1];//快捷键ctrl+alt+n
                return token;
                //快捷键sout输出；
            } catch (Exception e) {
            e.printStackTrace();
            }
            return null;
    }
    public GithubUser getUser(String accessToken){
            OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+ accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);//把string的JSON对象自动转换解析成JAVA的类对象
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
