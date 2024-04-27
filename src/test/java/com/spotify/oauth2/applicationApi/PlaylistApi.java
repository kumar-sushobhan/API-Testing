package com.spotify.oauth2.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;

public class PlaylistApi {

    public static Response post(Playlist requestPlaylist) {
        return RestResource.post("/users/316kaei6rmp2qiddy4phmdogmkdq/playlists", TokenManager.getToken(), requestPlaylist);
    }

    public static Response post(Playlist requestPlaylist, String token) {
        return RestResource.post("/users/316kaei6rmp2qiddy4phmdogmkdq/playlists", token, requestPlaylist);
    }

    public static Response get(String playlistID) {
        return RestResource.get(TokenManager.getToken(), "/playlists/" + playlistID);
    }

    public static Response put(Playlist requestPlaylist, String playlistID) {
        return RestResource.put(TokenManager.getToken(), "/playlists/" + playlistID, requestPlaylist);
    }

}
