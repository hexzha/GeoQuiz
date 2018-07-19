package com.bignerdranch.android.geoquiz;

public class TrueFalse {
    private int mQuestion;
    private boolean mTrueQuestion;
    private boolean mCheated;

    public TrueFalse(int question, boolean trueQuestion) {
        mQuestion = question;
        mTrueQuestion = trueQuestion;
        mCheated = false;
    }

    public boolean getCheating() {
        return mCheated;
    }

    public void setCheater() {
        mCheated = true;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }
}
