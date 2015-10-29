package com.example.john.jclprofilework.jclModule;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
*/

/**
 * Created by chialung on 2015/4/16.
 * �o��Loader�D�n�O���F�ΦXOkHttp���Ҧ��\��ӻs�@
 * �s�@�Ѧ� http://www.cnblogs.com/ct2011/p/4001708.html
 */
public class OkHttpLoader {

    private static final String CHARSET_NAME = "UTF-8";
    private static final int timeout = 30;  //��
    private static final OkHttpClient client = new OkHttpClient();

    static {
        client.setConnectTimeout(timeout, TimeUnit.SECONDS);
    }

    /**
     * ���}�Ҳ��B�u�{
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return client.newCall(request).execute();
    }

    /**
     * �}�Ҳ��B�u�{
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback){
        client.newCall(request).enqueue(responseCallback);
    }

    /**
     * �}�Ҳ��B�u�{, �L��^���G�]�Ū�Callback�^
     * @param request
     */
    public static void enqueue(Request request){
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }

    public static String getStringFromServer(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);

        if (response.isSuccessful()){
            String responseUrl = response.body().string();
            return  responseUrl;
        }else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * ?���ϥΤFHttpClinet��API�C�u�O?�F��K
     * @param params
     * @return
     */
/*    public static String formatParams(List<BasicNameValuePair> params){
        return URLEncodedUtils.format(params, CHARSET_NAME);
    }

    *//**
     * ?HttpGet �� url ��K���K�[�h?name value ??�C
     * @param url
     * @param params
     * @return
     *//*
    public static String attachHttpGetParams(String url, List<BasicNameValuePair> params){
        return url + "?" + formatParams(params);
    }*/

    /**
     * ?HttpGet �� url ��K���K�[1?name value ??�C
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String attachHttpGetParam(String url, String name, String value){
        return url + "?" + name + "=" + value;
    }
}
