package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {
    private MediaPlayer mMediaPlayer;
    private AudioManager am;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                        //am.abandonAudioFocus(afChangeListener);
                        releaseMediaPlayer();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                        mMediaPlayer.start();
                    }
                }
            };

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            am.abandonAudioFocus(afChangeListener);
        }
    }

    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>(Arrays.asList(
                new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red),
                new Word("green","chokokki", R.drawable.color_green, R.raw.color_green),
                new Word("brown","ṭakaakki", R.drawable.color_brown, R.raw.color_brown),
                new Word("gray","ṭopoppi", R.drawable.color_gray, R.raw.color_gray),
                new Word("black","kululli", R.drawable.color_black, R.raw.color_black),
                new Word("white","kelelli", R.drawable.color_white, R.raw.color_white),
                new Word("dusty yellow","ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow),
                new Word("mustard yellow","chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow)
        ));



        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);
        ListView listView = (ListView) rootView.findViewById(R.id.rootView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = words.get(position);
                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                int result = am.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                // Start the audio file
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getSoundId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
