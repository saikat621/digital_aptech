package com.digitalaptechapplication.feature.adduser

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.digitalaptechapplication.BR
import com.digitalaptechapplication.R
import com.digitalaptechapplication.base.BaseFragment
import com.digitalaptechapplication.databinding.FragmentUserListBinding
import com.digitalaptechapplication.feature.homebase.HomeBaseActivity
import com.digitalaptechapplication.feature.homebase.model.ToolbarChangesModel
import com.digitalaptechapplication.urility.hideSoftKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddUserFragment : BaseFragment<FragmentUserListBinding, AddUserViewModel>() {

    private lateinit var mContext: Context
    private var mAddUserViewModel: AddUserViewModel? = null

    private val vm by viewModel<AddUserViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_add_user

    override val viewModel: AddUserViewModel
        get() {
            mAddUserViewModel = vm //ViewModelProviders.of(this, BaseViewModelFactory { AddUserViewModel(mContext) }).get(AddUserViewModel::class.java)
            return mAddUserViewModel!!
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAddUserViewModel?.saveDataLiveData?.observe(viewLifecycleOwner, Observer {
            if (it.equals("success", ignoreCase = true)) {
                Toast.makeText(mContext, "Data saved successfully", Toast.LENGTH_SHORT).show()
                hideSoftKeyboard(mContext as HomeBaseActivity)

                Handler().postDelayed(Runnable {
                    (mContext as HomeBaseActivity).onBackPressed()
                }, 500)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (mContext as HomeBaseActivity).baseLiveData.value = ToolbarChangesModel(getString(R.string.add_user), true)
    }

}