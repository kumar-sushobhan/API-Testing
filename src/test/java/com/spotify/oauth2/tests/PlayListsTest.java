package com.spotify.oauth2.tests;

import com.spotify.oauth2.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListsTest {
    String playlistId;

    @Test
    public void shouldBeAbleToCreatePlaylist() {
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("Ols songs playlist");
        requestPlaylist.setDescription("Old songs");
        requestPlaylist.setPublic(false);
//        String payload = "{\n" +
//                "  \"name\": \"API Playlist2\",\n" +
//                "  \"description\": \"New playlist description2\",\n" +
//                "  \"public\": false\n" +
//                "}";
        Response response = PlaylistApi.post(requestPlaylist);
        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));

        playlistId = responsePlaylist.getId();
//                .body("name", equalTo("API Playlist2"),
//                        "description", equalTo("New playlist description2"),
//                        "public", equalTo(false));
    }

    @Test
    public void shouldBeAbleToGetPlaylist() {
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("Ols songs playlist");
        requestPlaylist.setDescription("Old songs");
        requestPlaylist.setPublic(false);
        Playlist responsePlaylist = PlaylistApi.get(playlistId).as(Playlist.class);
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
//                .body("name", equalTo("API Playlist2"),
//                        "description", equalTo("New playlist description2"),
//                        "public", equalTo(false));
    }

    @Test
    public void shouldBeAbleToUpdatePlaylist() {
//        String payload = "{\n" +
//                "  \"name\": \"API Playlist2\",\n" +
//                "  \"description\": \"New playlist description2\",\n" +
//                "  \"public\": false\n" +
//                "}";
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("Ols songs playlist update");
        requestPlaylist.setDescription("Old songs update");
        requestPlaylist.setPublic(true);
        Response responsePlaylist = PlaylistApi.put(requestPlaylist, playlistId);
        System.out.println(responsePlaylist);
        assertThat(responsePlaylist.statusCode(), equalTo(200));
    }

    @Test
    public void shouldNotBeAbleToCreatePlaylist() {
//        String payload = "{\n" +
//                "  \"name\": \"\",\n" +
//                "  \"description\": \"New playlist description2\",\n" +
//                "  \"public\": false\n" +
//                "}";

        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("");
        requestPlaylist.setDescription("Old songs with name");
        requestPlaylist.setPublic(false);

        Error error = PlaylistApi.post(requestPlaylist).as(Error.class);
        assertThat(error.getError().getStatus(), equalTo(400));
        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));

//                .body("error.status", equalTo(400),
//                        "error.message", equalTo("Missing required field: name"));
    }

    @Test
    public void shouldNotBeAbleToCreatePlaylistWithExpiredToken() {
        String invalid_token = "12345";
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("Ols songs playlist");
        requestPlaylist.setDescription("Old songs");
        requestPlaylist.setPublic(false);
        Error error = PlaylistApi.post(requestPlaylist, invalid_token).as(Error.class);
        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
    }
}
