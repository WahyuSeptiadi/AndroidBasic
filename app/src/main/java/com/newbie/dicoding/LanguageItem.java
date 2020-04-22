package com.newbie.dicoding;

public class LanguageItem {
    private int mImageResource;
    private String mLanguage;
    private String mDeskripsi;

    public LanguageItem(int mImageResource, String mLanguage, String mDeskripsi) {
        this.mImageResource = mImageResource;
        this.mLanguage = mLanguage;
        this.mDeskripsi = mDeskripsi;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmLanguage() {
        return mLanguage;
    }

    public String getmDeskripsi() {
        return mDeskripsi;
    }
}
