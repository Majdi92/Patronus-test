package de.patronus.test.presentation.viewmodel

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import de.patronus.test.domain.interactor.GetUserByIdUseCase
import de.patronus.test.presentation.fakes.FakePresentationData
import de.patronus.test.presentation.util.PresentationBaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserDetailViewModelTest : PresentationBaseTest() {


    @Mock
    lateinit var charactersUseCase: GetUserByIdUseCase

    @Mock
    private lateinit var observer: Observer<UserDetailUIModel>

    private lateinit var sut: UserDetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = UserDetailViewModel(
            dispatcher,
            charactersUseCase,
        )
        sut.getUser().observeForever(observer)
    }

    @Test
    fun `get user detail with user-id should return user complete detail from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val userId = "1"
            val character = FakePresentationData.getUsers(1)[0]
            Mockito.`when`(charactersUseCase(userId)).thenReturn(flowOf(character))

            // Act (When)
            sut.getUserDetail(userId)

            // Assert (Then)
            verify(observer).onChanged(UserDetailUIModel.Loading)
            verify(observer).onChanged(UserDetailUIModel.Success(character))
        }

    @Test
    fun `get user detail with user-id should return error from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val userId = "1"
            val errorMessage = "Internal server error"
            whenever(charactersUseCase(userId)) doAnswer { throw IOException(errorMessage) }

            // Act (When)
            sut.getUserDetail(userId)

            // Assert (Then)
            verify(observer).onChanged(UserDetailUIModel.Loading)
            verify(observer).onChanged(UserDetailUIModel.Error(errorMessage))
        }


}