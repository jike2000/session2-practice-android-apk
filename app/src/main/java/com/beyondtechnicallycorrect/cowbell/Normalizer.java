// SPDX-License-Identifier: GPL-3.0-or-later
// Adapted from Cowbell, Copyright (C) 2012 Jeffrey Charles.
package com.beyondtechnicallycorrect.cowbell;

import java.util.ArrayDeque;
import java.util.Queue;

final class Normalizer {
    private static final int WINDOW_SIZE = 8;
    private final Queue<Float> samples = new ArrayDeque<>(WINDOW_SIZE);

    void addValue(float value) {
        if (samples.size() == WINDOW_SIZE) {
            samples.remove();
        }
        samples.add(value);
    }

    float getNormalizedValue() {
        float sum = 0;
        for (float value : samples) {
            sum += value;
        }
        return sum / samples.size();
    }
}
