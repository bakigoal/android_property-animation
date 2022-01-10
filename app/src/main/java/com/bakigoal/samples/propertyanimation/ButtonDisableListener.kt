package com.bakigoal.samples.propertyanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.widget.Button

class ButtonDisableListener(private val button: Button): AnimatorListenerAdapter() {
    override fun onAnimationStart(animation: Animator?) {
        button.isEnabled = false
    }

    override fun onAnimationEnd(animation: Animator?) {
        button.isEnabled = true
    }
}