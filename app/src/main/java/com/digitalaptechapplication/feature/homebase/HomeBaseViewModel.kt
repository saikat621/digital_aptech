package com.digitalaptechapplication.feature.homebase

import android.text.TextUtils.replace
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.digitalaptechapplication.R
import com.digitalaptechapplication.base.BaseViewModel

class HomeBaseViewModel : BaseViewModel() {

    private var fragmentManager: FragmentManager? = null
    fun addFragment(mFragment: Fragment, appinBackground: Boolean, activity: AppCompatActivity) {
        try {
            fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.apply {
                add(R.id.fl_fragment_container, mFragment, mFragment.javaClass.simpleName)
                if (appinBackground)
                    commitAllowingStateLoss()
                else
                    commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun changeFragment(mFragment: Fragment, appinBackground: Boolean, activity: AppCompatActivity) {
        try {
            fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.apply {
                replace(R.id.fl_fragment_container, mFragment, mFragment::class.java.simpleName)
                addToBackStack(mFragment::class.java.simpleName)
                if (appinBackground)
                    commitAllowingStateLoss()
                else
                    commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun changeFragmentWithOutBackStack(mFragment: Fragment, appinBackground: Boolean, activity: AppCompatActivity) {
        try {
            fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.apply {
                replace(R.id.fl_fragment_container, mFragment, mFragment::class.java.simpleName)
                addToBackStack(null)
                if (appinBackground)
                    commitAllowingStateLoss()
                else
                    commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}