package com.digitalaptechapplication.feature.dashboard

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.digitalaptechapplication.BR
import com.digitalaptechapplication.R
import com.digitalaptechapplication.base.BaseFragment
import com.digitalaptechapplication.databinding.FragmentDashboardBinding
import com.digitalaptechapplication.feature.adduser.AddUserFragment
import com.digitalaptechapplication.feature.homebase.HomeBaseActivity
import com.digitalaptechapplication.feature.homebase.model.HomeBaseFragmentChangeModel
import com.digitalaptechapplication.feature.homebase.model.ToolbarChangesModel
import com.digitalaptechapplication.feature.userlist.UserListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    private lateinit var mContext: Context
    private var mDashboardViewModel: DashboardViewModel? = null

    private val vm by viewModel<DashboardViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_dashboard

    override val viewModel: DashboardViewModel
        get() {
            mDashboardViewModel = vm
            return mDashboardViewModel!!
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDashboardViewModel?.btnClickLiveData?.value = ""
        mDashboardViewModel?.btnClickLiveData?.observe(viewLifecycleOwner, Observer {
            if (it.equals("user_list", ignoreCase = true))
                (mContext as HomeBaseActivity).baseLiveData.value = HomeBaseFragmentChangeModel(UserListFragment(), "replace")
            else if (it.equals("add_user", ignoreCase = true))
                (mContext as HomeBaseActivity).baseLiveData.value = HomeBaseFragmentChangeModel(AddUserFragment(), "replace")
        })
    }

    override fun onResume() {
        super.onResume()
        (mContext as HomeBaseActivity).baseLiveData.value = ToolbarChangesModel("Dashboard", false)
    }
}