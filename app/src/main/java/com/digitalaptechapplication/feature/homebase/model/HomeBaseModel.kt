package com.digitalaptechapplication.feature.homebase.model

import androidx.fragment.app.Fragment
import java.io.Serializable

data class HomeBaseFragmentChangeModel(var fragment: Fragment?= null,
                                       var type: String = "") : Serializable

data class ToolbarChangesModel(var isScreenName: String = "",
                               var isShowBack: Boolean = false) : Serializable