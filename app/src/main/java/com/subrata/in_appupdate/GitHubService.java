package com.subrata.in_appupdate;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubService {
    @GET("releases/latest")
    Call<Release> getLatestRelease();
}
