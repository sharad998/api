package com.example.newsapiclient.domain.usecase

import com.example.newsapiclient.data.model.APIResponse
import com.example.newsapiclient.data.model.Article
import com.example.newsapiclient.data.utils.Resource
import com.example.newsapiclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSaveNewsUseCase(private val newsRepository: NewsRepository) {
   fun execute():Flow<List<Article>>{
       return newsRepository.getSavedNews()
   }

}