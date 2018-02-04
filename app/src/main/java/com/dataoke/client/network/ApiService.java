package com.dataoke.client.network;

import android.text.TextUtils;

import com.dataoke.client.MyApplication;
import com.dataoke.client.utils.NetUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ddllxy
 * @date 2018/1/22
 */
public class ApiService {

    public static final String BASE_URL = "http://news-at.zhihu.com/api/4/";

    //短缓存有效期为1分钟
    public static final int CACHE_STALE_SHORT = 60;
    //长缓存有效期为7天
    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;

    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=";

    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";
    private static CommonApi mCommonApi;
    private static volatile OkHttpClient mOkHttpClient;

    private ApiService() {

    }

    public static <T> T createApi(Class<T> clazz, String baseUrl) {
        if (mOkHttpClient == null) {
            synchronized (ApiService.class) {
                if (mOkHttpClient == null) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    // 指定缓存路径,缓存大小100Mb
                    Cache cache = new Cache(new File(MyApplication.sApplication.getCacheDir(), "HttpCache"),
                            1024 * 1024 * 100);
                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mCacheControlInterceptor)
                            .addNetworkInterceptor(mCacheControlInterceptor)
                            .addInterceptor(interceptor)
//                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    public static CommonApi createApi() {
        return createApi(CommonApi.class, BASE_URL);
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     */
    private static Interceptor mCacheControlInterceptor = chain -> {
        Request request = chain.request();
        if (!NetUtil.isNetworkConnected()) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetUtil.isNetworkConnected()) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            if (!TextUtils.isEmpty(cacheControl)) {
                return originalResponse.newBuilder().header("Cache-Control", cacheControl)
                        .removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder().header("Cache-Control", CACHE_CONTROL_AGE + CACHE_STALE_SHORT)
                        .removeHeader("Pragma").build();
            }
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_LONG)
                    .removeHeader("Pragma").build();
        }
    };

}
