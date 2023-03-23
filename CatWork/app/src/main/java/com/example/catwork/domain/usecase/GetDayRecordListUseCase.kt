package com.example.catwork.domain.usecase

import com.example.catwork.data.entity.DayRecordEntity
import com.example.catwork.data.repository.DayRecordRepository

class GetDayRecordListUseCase(private val dayRecordRepository: DayRecordRepository) {

    suspend operator fun invoke(year: Int, month: Int): List<DayRecordEntity> = dayRecordRepository.getDayRecordList(year, month)
}