package com.troshchii.pixabay.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface HitsRepository {
    fun load(): Flow<PagingData<Hit>>
}

class HitsRepositoryImpl @Inject constructor(
    private val pagingSource: HitsPagingSource
) : HitsRepository {

    override fun load(): Flow<PagingData<Hit>> = Pager(
        config = PagingConfig(pageSize = HitsPagingSource.PAGE_SIZE),
        pagingSourceFactory = { pagingSource }
    ).flow
}
