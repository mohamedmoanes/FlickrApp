package com.moanes.flickrapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.moanes.flickrapp.data.model.Photo
import com.moanes.flickrapp.data.repository.PhotosRepo
import com.moanes.flickrapp.ui.PhotosViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class PhotosViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var photosRepo: PhotosRepo

    @MockK
    private lateinit var loadingObserver: Observer<Boolean>

    @MockK
    private lateinit var noDataObserver: Observer<Boolean>

    @MockK
    private lateinit var errorsObserver: Observer<String>

    @MockK
    private lateinit var photosObserver: Observer<List<Photo>>

    private lateinit var subject: PhotosViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        subject = PhotosViewModel(photosRepo)
    }

    @Test
    fun `getPhotos success`() {
        // given
        coEvery { photosRepo.getLocalPhotos() } returns getPhotos()

        subject.photosLiveData.observeForever(photosObserver)
        subject.showLoading.observeForever(loadingObserver)
        subject.showNoData.observeForever(noDataObserver)

        //when
        subject.getPhotos()

        // then
        verify { loadingObserver.onChanged(true) }
        coVerify { photosRepo.getRemoteData(1) }
        verify { noDataObserver.onChanged(false) }
        verify { photosObserver.onChanged(getPhotos().value) }
        verify { loadingObserver.onChanged(false) }
    }

    @Test
    fun `getPhotos Empty`() {
        // given
        coEvery { photosRepo.getLocalPhotos() } returns getPhotosEmpty()

        subject.photosLiveData.observeForever(photosObserver)
        subject.showLoading.observeForever(loadingObserver)
        subject.showNoData.observeForever(noDataObserver)

        //when
        subject.getPhotos()

        // then
        verify { loadingObserver.onChanged(true) }
        coVerify { photosRepo.getRemoteData(1) }
        verify { noDataObserver.onChanged(true) }
        verify { photosObserver.onChanged(getPhotos().value) }
        verify { loadingObserver.onChanged(false) }
    }

    @Test
    fun `getPhotos failure`() {
        // given
        coEvery { photosRepo.getRemoteData(1) } throws IOException()

        subject.photosLiveData.observeForever(photosObserver)
        subject.errorLiveData.observeForever(errorsObserver)
        subject.showLoading.observeForever(loadingObserver)

        //when
        subject.getPhotos()

        // then
        verify { loadingObserver.onChanged(true) }
        coVerify { photosRepo.getRemoteData(1) }
        verify { loadingObserver.onChanged(false) }
        verify { errorsObserver.onChanged(IOException().localizedMessage) }

    }
}