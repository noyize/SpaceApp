package com.noyal.spaceapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noyal.spaceapp.data.Picture
import com.noyal.spaceapp.repositories.SpaceNewsRepository
import com.noyal.spaceapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val spaceNewsRepository: SpaceNewsRepository) :
    ViewModel() {

    private val _spaceNews = MutableStateFlow<Resource<List<Picture>>>(Resource.Empty)
    val spacePicture: StateFlow<Resource<List<Picture>>> = _spaceNews

    private fun getSpaceNews() {
        viewModelScope.launch {
            _spaceNews.emit(Resource.Loading)
            spaceNewsRepository.getSpaceNews().catch { exception ->
                _spaceNews.emit(Resource.Error(error = exception.message))
            }.collect { pictureList ->
                _spaceNews.emit(Resource.Success(pictureList.sortedByDescending { it.date }))
            }
        }
    }

    init {
        getSpaceNews()
    }

    fun getNews(position: Int) {
        return
    }
}