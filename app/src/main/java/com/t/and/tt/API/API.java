package com.t.and.tt.API;


import com.t.and.tt.Model.GoogleLogIn.ContentGoogle;
import com.t.and.tt.Model.GoogleLogIn.GoogleLogInModel;
import com.t.and.tt.Model.Home.Banner.BannerHomeModel;
import com.t.and.tt.Model.Home.Services.HomeServicesModel;
import com.t.and.tt.Model.LogIn.LogInModel;
import com.t.and.tt.Model.Profile.ProfileModel;
import com.t.and.tt.Model.SearchBusiness.SearchBusinessModel;
import com.t.and.tt.Model.SignUp.SignUpModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {


    @FormUrlEncoded
    @POST("login")
    Call<LogInModel> Login(@Field("email") String email, @Field("password") String password, @Field("device_type") String device_type, @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST("social_login")
    Call<GoogleLogInModel> googleLogIN(@Field("name") String name, @Field("email") String email, @Field("contact_no") String contact_no, @Field("device_type") String device_type, @Field("device_token") String device_token, @Field("google_id") String google_id);


    @FormUrlEncoded
    @POST("register")
    Call<SignUpModel> SignUp(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("contact_no") String phone,@Field("device_type") String device_type,@Field("device_token") String device_token);

    @FormUrlEncoded
    @POST("dashboard_image")
    Call<BannerHomeModel> getBanner(@Field("user_id") String user_id, @Header("Authorization")String Authorization, @Header("Accept")String Accept);


    @POST("services")
    Call<HomeServicesModel> getService();
//
    @FormUrlEncoded
    @POST("search_business")
    Call<SearchBusinessModel> getSearchBusiness(@Field("user_id") String user_id, @Header("authorization")String Authorization, @Header("Accept")String Accept);


    @Multipart
    @POST("update_profile")
    Call<ProfileModel> updateProfile(
            @Part("name") RequestBody name,
            @Part("user_id") RequestBody user_id,
            @Part("phone") RequestBody phone,
            @Part MultipartBody.Part Profile_image,
            @Header("userToken")String userToken);

//
//    @POST("search_business")
//    Call<SearchBusinessModel> getSearchBusiness();



}
