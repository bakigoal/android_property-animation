package com.bakigoal.samples.propertyanimation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.samples.propertyanimation.R
import com.google.samples.propertyanimation.databinding.FragmentAnimationBinding

class AnimationFragment : Fragment() {

    private lateinit var binding: FragmentAnimationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_animation, container, false)
        binding.animationFragment = this
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    fun rotate() {
        val animator = ObjectAnimator.ofFloat(binding.star, View.ROTATION, -360f, 0f)
        animator.duration = DateUtils.SECOND_IN_MILLIS
        animator.disableViewDuringAnimation(binding.rotateButton)
        animator.start()
    }

    fun translate() {
        val animator = ObjectAnimator.ofFloat(binding.star, View.TRANSLATION_X, 200f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(binding.translateButton)
        animator.start()
    }

    fun scale() {
    }

    fun fade() {
    }

    fun colorize() {
    }

    fun shower() {
    }

}

