package com.example.android.miwok;

/**
 * Created by yhous on 18/05/2017.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mRessourceId = NO_IMAGE_PROVIDED;
    private int mSoundId;


    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    public Word(String defaultTranslation, String miwokTranslation, int soundId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mSoundId = soundId;

    }

    public Word(String defaultTranslation, String miwokTranslation, int ressourceId, int soundId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mRessourceId = ressourceId;
        mSoundId = soundId;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
    public int getRessourceId() {
        return mRessourceId;
    }
    public int getSoundId() {
        return mSoundId;
    }

    public boolean hasImage() {
        return mRessourceId != NO_IMAGE_PROVIDED;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }
}
