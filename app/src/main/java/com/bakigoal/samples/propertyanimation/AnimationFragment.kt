package com.bakigoal.samples.propertyanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color.BLACK
import android.graphics.Color.RED
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.samples.propertyanimation.R
import com.google.samples.propertyanimation.databinding.FragmentAnimationBinding

const val BACKGROUND_COLOR = "backgroundColor"

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
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(binding.star, scaleX, scaleY)
        animator.repeatCount = 3
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(binding.scaleButton)
        animator.start()
    }

    fun fade() {
        val animator = ObjectAnimator.ofFloat(binding.star, View.ALPHA, 0f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(binding.fadeButton)
        animator.duration = DateUtils.SECOND_IN_MILLIS
        animator.start()
    }

    fun colorize() {
        val animator = ObjectAnimator.ofArgb(binding.star.parent, BACKGROUND_COLOR, BLACK, RED)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(binding.colorizeButton)
        animator.duration = DateUtils.SECOND_IN_MILLIS / 2
        animator.start()
    }

    fun shower() {
        val star = binding.star
        val container = star.parent as ViewGroup
        val containerW = container.width
        val containerH = container.height
        var starW: Float = star.width.toFloat()
        var starH: Float = star.height.toFloat()

        val newStar = AppCompatImageView(requireActivity())
        newStar.setImageResource(R.drawable.ic_star)
        newStar.layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        newStar.scaleX = Math.random().toFloat() * 1.5f + .1f
        newStar.scaleY = newStar.scaleX
        starW *= newStar.scaleX
        starH *= newStar.scaleY
        newStar.translationX = Math.random().toFloat() * containerW - starW / 2

        val mover = ObjectAnimator.ofFloat(newStar, View.TRANSLATION_Y, -starH, containerH + starH)
        mover.interpolator = AccelerateInterpolator(1f)
        val rotator = ObjectAnimator.ofFloat(newStar, View.ROTATION, (Math.random() * 1080).toFloat())
        rotator.interpolator = LinearInterpolator()

        val set = AnimatorSet()
        set.playTogether(mover, rotator)
        set.duration = (Math.random() * 1500 + 500).toLong()

        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                container.removeView(newStar)
            }
        })
        set.start()

        container.addView(newStar)
    }

}

