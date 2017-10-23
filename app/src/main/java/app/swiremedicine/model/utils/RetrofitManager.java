package app.swiremedicine.model.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 王坤 on 2017/10/23.
 */

public class RetrofitManager {
    private Retrofit mretrofit;
    public static final String BASE_URL = "";

    public static class HolderRetrofit {
        public static RetrofitManager retrofitManager = new RetrofitManager(BASE_URL);
    }

    public static RetrofitManager GetManager() {
        return HolderRetrofit.retrofitManager;
    }

    public static RetrofitManager get_manager(String baseurl) {
        return new RetrofitManager(baseurl);
    }

    private RetrofitManager(String baseurl) {
        this.mretrofit = buildRetrofit(baseurl);
    }

    public Retrofit buildRetrofit(String baseurl) {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return builder;
    }

    public <T> T create(Class<T> clazz) {
        return mretrofit.create(clazz);
    }
}
