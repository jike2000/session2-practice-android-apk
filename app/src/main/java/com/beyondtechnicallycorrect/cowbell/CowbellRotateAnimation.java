// SPDX-License-Identifier: GPL-3.0-or-later
// Adapted from Cowbell, Copyright (C) 2012 Jeffrey Charles.
package com.beyondtechnicallycorrect.cowbell;

import android.view.animation.RotateAnimation;

final class CowbellRotateAnimation extends RotateAnimation {
    CowbellRotateAnimation(float fromDegrees, float toDegrees) {
        super(fromDegrees, toDegrees, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.21f);
        setFillAfter(true);
        setDuration(110);
    }
}
