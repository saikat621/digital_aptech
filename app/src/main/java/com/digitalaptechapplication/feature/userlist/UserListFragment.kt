package com.digitalaptechapplication.feature.userlist

import android.content.Context
import com.digitalaptechapplication.BR
import com.digitalaptechapplication.R
import com.digitalaptechapplication.base.BaseFragment
import com.digitalaptechapplication.databinding.FragmentUserListBinding
import com.digitalaptechapplication.feature.homebase.HomeBaseActivity
import com.digitalaptechapplication.feature.homebase.model.ToolbarChangesModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : BaseFragment<FragmentUserListBinding, UserListViewModel>() {

    private var mUserListViewModel: UserListViewModel? = null
    private lateinit var mContext: Context

    private val vm by viewModel<UserListViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_user_list

    override val viewModel: UserListViewModel
        get() {
            mUserListViewModel = vm
            return mUserListViewModel!!
        }

    override fun onResume() {
        super.onResume()
        (mContext as HomeBaseActivity).baseLiveData.value = ToolbarChangesModel(getString(R.string.user_list), true)
    }
}