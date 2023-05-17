package de.patronus.test.ui.ui.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import de.patronus.test.databinding.FragmentUserListBinding
import de.patronus.test.presentation.viewmodel.BaseViewModel
import de.patronus.test.presentation.viewmodel.UsersListUIModel
import de.patronus.test.presentation.viewmodel.UsersListViewModel
import de.patronus.test.ui.base.BaseFragment
import de.patronus.test.ui.extension.observe
import javax.inject.Inject


@AndroidEntryPoint
class UsersListFragment : BaseFragment<FragmentUserListBinding, BaseViewModel>() {

    override val viewModel: UsersListViewModel by viewModels()

    override fun getViewBinding(): FragmentUserListBinding =
        FragmentUserListBinding.inflate(layoutInflater)

    @Inject
    lateinit var userAdapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllUsers()
        observe(viewModel.getUsers(), ::onViewStateChange)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvUsers.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        userAdapter.setItemClickListener { user ->
            findNavController().navigate(
                UsersListFragmentDirections.actionUserListFragmentToUserDetailFragment(
                    user.id!!
                )
            )
        }
    }

    private fun onViewStateChange(event: UsersListUIModel) {
        if (event.isRedelivered) return
        when (event) {
            is UsersListUIModel.Error -> handleErrorMessage(event.error)
            is UsersListUIModel.Loading -> handleLoading(true)
            is UsersListUIModel.Success -> {
                handleLoading(false)
                event.data.let {
                    userAdapter.list = it
                }
            }
        }
    }
}