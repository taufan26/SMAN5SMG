package app.klikgss.sman5smg.api;

import app.klikgss.sman5smg.model.login.Login;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
      @Field("username") String username,
      @Field("password") String password
    );
}
