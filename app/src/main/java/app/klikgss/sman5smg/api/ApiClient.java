package app.klikgss.sman5smg.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://sman5semarang.klikgss.com/backend/API/LoginBaru/";

    private static Retrofit retrofit;

    public static Retrofit getClient(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
