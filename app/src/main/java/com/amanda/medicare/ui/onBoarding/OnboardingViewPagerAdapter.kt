package com.amanda.medicare.ui.onBoarding

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.amanda.medicare.R

class OnboardingViewPagerAdapter (fragmentActivity: FragmentActivity, private val context: Context) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment.newInstance(
                context.resources.getString(R.string.heading_onboarding_first),
                context.resources.getString(R.string.body_onboarding_first),
                R.drawable.onboard1
            )
            1 -> OnboardingFragment.newInstance(
                context.resources.getString(R.string.heading_onboarding_second),
                context.resources.getString(R.string.body_onboarding_second),
                R.drawable.onboard2
            )
            else -> OnboardingFragment.newInstance(
                context.resources.getString(R.string.heading_onboarding_third),
                context.resources.getString(R.string.body_onboarding_third),
                R.drawable.onboard3
            )
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}