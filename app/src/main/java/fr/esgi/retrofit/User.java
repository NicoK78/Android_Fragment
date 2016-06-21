package fr.esgi.retrofit;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mohsan on 10/05/16.
 */

public class User implements Serializable{

    public String name;
    public String login;
    @SerializedName("avatar_url")
    public String avatarUrl;
    public String followers;
    @SerializedName("public_repos")
    public int public_repos;

    public User(String name, String login, String avatarUrl, String followers) {
        this.name = name;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.followers = followers;
        Log.d(name+" "+login+" "+avatarUrl+" "+followers, "USER");
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getFollowers() {
        return followers;
    }

    public int getPublic_repos() { return public_repos; }
}
