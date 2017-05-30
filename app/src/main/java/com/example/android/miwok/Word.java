package com.example.android.miwok;

/**
 * Created by yhous on 18/05/2017.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mRessourceId = NO_IMAGE_PROVIDED;


    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    public Word(String defaultTranslation, String miwokTranslation, int ressourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mRessourceId = ressourceId;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
    public int getRessourceId() {
        return mRessourceId;
    }

    public boolean hasImage() {
        return mRessourceId != NO_IMAGE_PROVIDED;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }
}
