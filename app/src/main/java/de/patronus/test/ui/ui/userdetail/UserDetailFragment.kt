package de.patronus.test.ui.ui.userdetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import de.patronus.test.R
import de.patronus.test.databinding.FragmentUserDetailsBinding
import de.patronus.test.presentation.viewmodel.BaseViewModel
import de.patronus.test.presentation.viewmodel.UserDetailUIModel
import de.patronus.test.presentation.viewmodel.UserDetailViewModel
import de.patronus.test.ui.base.BaseFragment
import de.patronus.test.ui.extension.observe
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailsBinding, BaseViewModel>() {

    override val viewModel: UserDetailViewModel by viewModels()

    override fun getViewBinding(): FragmentUserDetailsBinding =
        FragmentUserDetailsBinding.inflate(layoutInflater)

    private val args: UserDetailFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserDetail(args.userId)
        observe(viewModel.getUser(), ::onViewStateChange)
        initUI()
    }

    private fun initUI() {
        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
        val opacity = (0.5 * 255).toInt()
        binding.userDetailTvBan.background.alpha = opacity

    }

    private fun onViewStateChange(result: UserDetailUIModel) {
        if (result.isRedelivered) return
        when (result) {
            is UserDetailUIModel.Error -> handleErrorMessage(result.error)
            UserDetailUIModel.Loading -> handleLoading(true)
            is UserDetailUIModel.Success -> {
                handleLoading(false)
                result.data.let { user ->
                    binding.apply {
                        userDetailTvAddress.text = user.address.toString()
                        userDetailTvName.text = user.firstName + " " + user.lastName
                        userDetailTvPhone.text = user.phoneNumber
                        userDetailTvGender.text = user.gender
                        glide.load(user.image).into(userDetailIvPhoto)

                        if (user.stickers != null) {
                            if (user.stickers!!.contains(getString(R.string.fam))) {
                                userDetailTvFam.visibility = View.VISIBLE
                            } else {
                                userDetailTvFam.visibility = View.GONE
                            }
                            if (user.stickers!!.contains(getString(R.string.ban))) {
                                userDetailTvBan.visibility = View.VISIBLE
                            } else {
                                userDetailTvBan.visibility = View.GONE
                            }
                        }
                    }
                }
            }

        }
    }
}