package com.noyal.spaceapp.repositories

import com.noyal.spaceapp.data.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SpaceNewsRepository @Inject constructor(private val spaceNews: List<News>) {

    fun getSpaceNews(): Flow<List<News>> {
        return flow {
            emit(spaceNews)
        }.flowOn(Dispatchers.IO)
    }
}