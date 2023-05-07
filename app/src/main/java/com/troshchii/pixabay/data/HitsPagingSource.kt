package com.troshchii.pixabay.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.troshchii.pixabay.network.ApiService
import com.troshchii.pixabay.network.toUi
import javax.inject.Inject
import javax.inject.Named

class HitsPagingSource @Inject constructor(
    @Named("ApiKey") private val apiKey: String,
    private val service: ApiService,
) : PagingSource<Int, Hit>() {

    companion object {
        const val INITIAL_PAGE_NUMBER = 1

        // small value to see loading more better
        const val PAGE_SIZE = 4
    }

    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        val prevKey = params.key ?: INITIAL_PAGE_NUMBER

        val result = service.getHits(
            key = apiKey,
            q = "yellow+flowers",
            imageType = "photo",
            safeSearch = true,
            page = prevKey,
            perPage = PAGE_SIZE
        )

        val body = result.body()

        return if (result.isSuccessful && body != null) {
            val hits: List<Hit> = body.toUi()

            LoadResult.Page(
                hits,
                prevKey = null,
                nextKey = prevKey + 1
            )
        } else {
            LoadResult.Error(Throwable(result.message()))
        }
    }
}
