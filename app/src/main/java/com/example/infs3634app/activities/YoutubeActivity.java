package com.example.infs3634app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infs3634app.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
//code referenced from https://www.youtube.com/watch?v=0uXuByBwv8E
public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    private YouTubePlayerView youTubePlayerView;

    final String API_KEY = "AIzaSyAGuQAXeEzz3dcfu9FavEzJG4_fyVXb_EI";
    final String playlist_id = "PLLALQuK1NDrg2D1BpRhd2N1Etf_ytM-Qq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        youTubePlayerView = findViewById(R.id.youtubePlayer);
        youTubePlayerView.initialize(API_KEY, this);
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result){
        Toast.makeText(this, "Sorry the video has failed to load. " +
                "Please try again later.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored){
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        if(!wasRestored){
            player.cuePlaylist(playlist_id);
        }
    }
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
        }
        @Override
        public void onVideoStarted() {
        }
    };
}

