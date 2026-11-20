// SPDX-License-Identifier: GPL-3.0-or-later
// Adapted from Cowbell, Copyright (C) 2012 Jeffrey Charles.
package com.beyondtechnicallycorrect.cowbell;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public final class CowbellActivity extends Activity implements SensorEventListener {
    private static final String TAG = "CowbellActivity";
    private static final int SWING_ANGLE = 30;
    private static final int RING_THRESHOLD = 35;
    private static final int CENTER_THRESHOLD = 25;

    private SensorManager sensors;
    private Sensor accelerometer;
    private ImageView bell;
    private MediaPlayer sound;
    private Normalizer rollSamples;
    private RotationPosition position = RotationPosition.CENTRE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        bell = findViewById(R.id.cowbell);
        sensors = getSystemService(SensorManager.class);
        accelerometer = sensors.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rollSamples = new Normalizer();
        position = RotationPosition.CENTRE;
        sound = MediaPlayer.create(this, R.raw.cowbell);
        if (accelerometer != null) {
            sensors.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        sensors.unregisterListener(this);
        bell.clearAnimation();
        if (sound != null) {
            sound.release();
            sound = null;
        }
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        // The lateral component detects a swing without requiring a magnetometer.
        rollSamples.addValue((float) Math.toDegrees(Math.atan2(-x, Math.hypot(y, z))));
        float roll = rollSamples.getNormalizedValue();

        RotationPosition next = position;
        if (roll <= -RING_THRESHOLD) {
            next = RotationPosition.LEFT;
        } else if (roll >= RING_THRESHOLD) {
            next = RotationPosition.RIGHT;
        } else if (Math.abs(roll) <= CENTER_THRESHOLD) {
            next = RotationPosition.CENTRE;
        }

        if (next != position) {
            bell.startAnimation(new CowbellRotateAnimation(angle(position), angle(next)));
            position = next;
            if (next != RotationPosition.CENTRE && sound != null) {
                if (sound.isPlaying()) {
                    sound.seekTo(0);
                }
                sound.start();
                Log.d(TAG, "Cowbell sound started");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // The roll average smooths the input samples.
    }

    private static int angle(RotationPosition position) {
        switch (position) {
            case LEFT: return SWING_ANGLE;
            case RIGHT: return -SWING_ANGLE;
            default: return 0;
        }
    }
}
