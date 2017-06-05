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
public class FamilyFragment extends Fragment {
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

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>(Arrays.asList(
                new Word("father", "әpә", R.drawable.family_father, R.raw.family_father),
                new Word("mother","әṭa", R.drawable.family_mother, R.raw.family_mother),
                new Word("son","angsi", R.drawable.family_son, R.raw.family_son),
                new Word("daughter","tune", R.drawable.family_daughter, R.raw.family_daughter),
                new Word("older brother","taachi", R.drawable.family_older_brother, R.raw.family_older_brother),
                new Word("younger brother","chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother),
                new Word("older sister","teṭe", R.drawable.family_older_sister, R.raw.family_older_sister),
                new Word("younger sister","kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister),
                new Word("grandmother","ama", R.drawable.family_grandmother, R.raw.family_grandmother),
                new Word("grandfather","paapa", R.drawable.family_grandfather, R.raw.family_grandmother)
        ));



        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
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
                        AudioManager.AUDIOFOCUS_GAIN);
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
