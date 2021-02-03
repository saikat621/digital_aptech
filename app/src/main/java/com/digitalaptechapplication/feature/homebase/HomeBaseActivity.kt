package com.digitalaptechapplication.feature.homebase

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.digitalaptechapplication.R
import com.digitalaptechapplication.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar_layout.*
import com.digitalaptechapplication.BR
import com.digitalaptechapplication.databinding.ActivityHomeBaseBinding
import com.digitalaptechapplication.feature.dashboard.DashboardFragment
import com.digitalaptechapplication.feature.homebase.model.HomeBaseFragmentChangeModel
import com.digitalaptechapplication.feature.homebase.model.ToolbarChangesModel
import com.digitalaptechapplication.urility.hideSoftKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeBaseActivity : BaseActivity<ActivityHomeBaseBinding, HomeBaseViewModel>() {

    private var mHomeViewModel: HomeBaseViewModel? = null
    private var appInBackground = false
    private var back_pressed = 0L

    val baseLiveData: MutableLiveData<Any> by lazy {
        MutableLiveData<Any>()
    }

    private val vm by viewModel<HomeBaseViewModel>()

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_home_base

    override val viewModel: HomeBaseViewModel
        get() {
            mHomeViewModel = vm
            return mHomeViewModel!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        viewDataBinding?.lifecycleOwner = this

        baseLiveData.observe(this, Observer {
            if (it is HomeBaseFragmentChangeModel && it.fragment != null) {
                when {
                    it.type.equals("replace", ignoreCase = true) -> mHomeViewModel?.changeFragment(it.fragment!!, appInBackground, this)
                    it.type.equals("add", ignoreCase = true) -> mHomeViewModel?.addFragment(it.fragment!!, appInBackground, this)
                    else -> mHomeViewModel?.changeFragmentWithOutBackStack(it.fragment!!, appInBackground, this)
                }
            }
            else if (it is ToolbarChangesModel) {
                tv_title.text = it.isScreenName
                if (it.isShowBack)
                    iv_back_icon.visibility = View.VISIBLE
                else
                    iv_back_icon.visibility = View.GONE
            }
        })

        baseLiveData.value = HomeBaseFragmentChangeModel(DashboardFragment(), "replace")

        iv_back_icon.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        appInBackground = false
    }

    override fun onPause() {
        super.onPause()
        appInBackground = true
    }

    val currentFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.fl_fragment_container)

    override fun onBackPressed() {
        hideSoftKeyboard(this)
        val entryCount = supportFragmentManager.backStackEntryCount
        if (entryCount == 0 && currentFragment != null && currentFragment !is DashboardFragment)
            baseLiveData.value = HomeBaseFragmentChangeModel(DashboardFragment(), "")
        else if (currentFragment != null && currentFragment is DashboardFragment) {
            if (back_pressed + 2000 > System.currentTimeMillis()) {
                super.onBackPressed()
                finish()
                overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )

            } else
                Toast.makeText(this, getString(R.string.press_again_exit), Toast.LENGTH_SHORT).show()

            back_pressed = System.currentTimeMillis()
        }
        else
            super.onBackPressed()
    }
}
