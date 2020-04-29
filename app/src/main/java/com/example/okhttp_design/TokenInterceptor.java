package com.example.okhttp_design;

import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 1.token验证是为了提高app的安全性，需求规定access_token的有效期为1小时，refresh_Token的有效期为7天;
 * 2.如果access_token1个小时后过期了，服务器会返回400或者401，此时客户端要根据刷新access_token的retrofit接口去重新请求新的access_token;
 * 3.如果refresh_Token7天后也过期了，则要求跳到登录页面
 * <p>
 * TODO 此处指存在refresh_Token 这个情况
 */
public class TokenInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String path = request.url().encodedPath();

//        if (isMembers(path)){ //凡是接口中包含Members都添加到拦截器，如果需求不同，这里需要改动。
//            //加拦截器,旧的token是token的key,这个要问清楚后台
//            request = request.newBuilder().addHeader("旧的token", SharedPreUtil.getString(Global.mContext, "获取的token", "")).build();
//        }
        /**
         *判断刷新token连接，直接返回，不走下面代码，避免死循环。
         */
//        if (request.url().toString().contains("/tokens接口") && request.method() == "GET"){
//            return  chain.proceed(request) ;
//        }
        //拦截了响应体
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        if (!HttpHeaders.hasBody(response)) {
            //END HTTP
        } else if (bodyEncoded(response.headers())) {
            //HTTP (encoded body omitted)
        } else {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    return response;
                }
            }
            if (!isPlaintext(buffer)) {
                return response;
            }
            if (contentLength != 0) {
                //获取到response的body的string字符串
                String result = buffer.clone().readString(charset);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    //当状态码返回的是400或者401,即代表过期
                    if (code.equals("700")) {
//                    if (!path.contains("接口")){//代表不是刷新token
//                        //获取新的token
//                        String membership_uuid = SharedPreUtil.getString(Global.mContext, "获取的id", "");                        String refresh_token = SharedPreUtil.getString(Global.mContext, "获取的token", "");
//                        retrofit2.Response<JsonObject> execute = RetrofitManager.getInstance().getHttpService().getNewToken(获取的id, 获取的token).execute();
//                        if (execute.code()==200) {
//                            JsonObject body = execute.body();
//                            //解析这个JsonObject，得到新的token
//                            String new_token = body.get("获取的token").getAsString();
//                            //保存 access_token，覆盖旧的accesss_token
//                            SharedPreUtil.saveString(Global.mContext, "获取的token", new_token);
//                            LogUtil.e("----------", new_token);
//                            //重新发起请求
//                            Request newRequest = request.newBuilder()
//                                    .removeHeader("旧的token")   //移除旧的token
//                                    .header("旧的token", new_token)  //添加新的token
//                                    .build();
//
//                            return chain.proceed(newRequest);//重新发起请求，此时是新的token
//                        }else{
                        //要求用户直接登录
                        //TODO 跳转页面，提示登录
//                        }
//                    }else {
//                        return response;
//                    }
                    } else {
                        //此时没有过期
                        Log.d("============", "intercept: token ------------");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    //项目中所有包含members的接口
    public boolean isMembers(String path) {
        if (path.contains("token接口")) {
            return true;
        }
        return false;
    }
}
