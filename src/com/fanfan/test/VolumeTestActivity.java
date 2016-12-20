package com.fanfan.voltest;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;


public class VolumeTestActivity extends Activity
    implements View.OnClickListener
{
    private static final String TAG = "VolumeTestActivity";

    private AudioManager  mAudioManager;
    private Button        mBtnCallVolInc;
    private Button        mBtnCallVolDec;
    private Button        mBtnModeInCall;
    private Button        mBtnModeNormal;
    private TextView      mTextCallVol;
    private TextView      mTextAudioMode;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mAudioManager  = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        mBtnCallVolInc = (Button)findViewById(R.id.btn_voice_call_vol_inc);
        mBtnCallVolDec = (Button)findViewById(R.id.btn_voice_call_vol_dec);
        mBtnModeInCall = (Button)findViewById(R.id.btn_mode_in_call      );
        mBtnModeNormal = (Button)findViewById(R.id.btn_mode_normal       );
        mTextCallVol   = (TextView)findViewById(R.id.txt_voice_call_vol  );
        mTextAudioMode = (TextView)findViewById(R.id.txt_audio_mode      );

        mBtnCallVolInc.setOnClickListener(this);
        mBtnCallVolDec.setOnClickListener(this);
        mBtnModeInCall.setOnClickListener(this);
        mBtnModeNormal.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_voice_call_vol_inc:
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_VOICE_CALL,  1, 0);
            break;
        case R.id.btn_voice_call_vol_dec:
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_VOICE_CALL, -1, 0);
            break;
        case R.id.btn_mode_in_call:
            mAudioManager.setMode(AudioManager.MODE_IN_CALL);
            break;
        case R.id.btn_mode_normal:
            mAudioManager.setMode(AudioManager.MODE_NORMAL);
            break;
        }
        updateUI();
    }

    private void updateUI() {
        mTextCallVol  .setText("" + mAudioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL));
        mTextAudioMode.setText(mAudioManager.getMode() == AudioManager.MODE_IN_CALL ? "in call" : "normal");
    }
}




