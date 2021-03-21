package com.noyal.spaceapp.repositories

import com.noyal.spaceapp.data.Picture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SpaceNewsRepository @Inject constructor(private val spaceNews: List<Picture>) {

    fun getSpaceNews(): Flow<List<Picture>> {
        return flow {
            emit(spaceNews)
        }.flowOn(Dispatchers.IO)
    }
}