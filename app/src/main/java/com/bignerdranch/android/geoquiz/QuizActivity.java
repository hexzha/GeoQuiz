package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        if(data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false)) {
            mQuestionBank[mCurrentIndex].setCheater();
        }
    }

    private void updateQuestion() {
        int quesiton = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(quesiton);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = 0;

        if (mIsCheater) {
            messageResId = R.string.judgement_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mPrevButton = (Button) findViewById(R.id.previous_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex != 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                } else {
                    mCurrentIndex = (mCurrentIndex + 4) % mQuestionBank.length;
                }
                updateQuestion();
            }
        });

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                startActivityForResult(intent, 0);
            }
        });

        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }
}

/**
 * import android.os.Bundle;
 * import android.support.design.widget.FloatingActionButton;
 * import android.support.design.widget.Snackbar;
 * import android.support.v7.app.AppCompatActivity;
 * import android.support.v7.widget.Toolbar;
 * import android.view.View;
 * import android.view.Menu;
 * import android.view.MenuItem;
 * <p>
 * public class QuizActivity extends AppCompatActivity {
 *
 * @Override protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState);
 * setContentView(R.layout.activity_quiz);
 * Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
 * setSupportActionBar(toolbar);
 * <p>
 * FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
 * fab.setOnClickListener(new View.OnClickListener() {
 * @Override public void onClick(View view) {
 * Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
 * .setAction("Action", null).show();
 * }
 * });
 * }
 * @Override public boolean onCreateOptionsMenu(Menu menu) {
 * // Inflate the menu; this adds items to the action bar if it is present.
 * getMenuInflater().inflate(R.menu.menu_quiz, menu);
 * return true;
 * }
 * @Override public boolean onOptionsItemSelected(MenuItem item) {
 * // Handle action bar item clicks here. The action bar will
 * // automatically handle clicks on the Home/Up button, so long
 * // as you specify a parent activity in AndroidManifest.xml.
 * int id = item.getItemId();
 * <p>
 * //noinspection SimplifiableIfStatement
 * if (id == R.id.action_settings) {
 * return true;
 * }
 * <p>
 * return super.onOptionsItemSelected(item);
 * }
 * }
 **/