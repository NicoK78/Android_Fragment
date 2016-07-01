package fr.esgi.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by mohsan on 10/05/16.
 */
public interface GitHubService {

    String END_POINT = "https://api.github.com";

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("/users/{username}/repos")
    Call<List<Repo>> getRepos(@Path("username") String username);

}
