package com.example.myapplication

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.model.Article
import com.example.myapplication.network.NewsApi
import java.lang.Exception

class NewsPagingDataSource(private val service: NewsApi): PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val pageIndex = params.key ?: 1
        return try {
            val response = service.getListNews("bitcoin", "eaf0ed5151ec425098796b4b0e862245", params.loadSize, pageIndex).execute()
            val articles = response.body()!!.articles
            return LoadResult.Page(
                data = articles,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = if (articles.isEmpty()) null else pageIndex + 1
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}