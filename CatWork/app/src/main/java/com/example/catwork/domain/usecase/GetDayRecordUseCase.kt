package com.example.catwork.domain.usecase

import com.example.catwork.data.entity.DayRecordEntity
import com.example.catwork.data.repository.DayRecordRepository

class GetDayRecordUseCase(private val dayRecordRepository: DayRecordRepository) {

    suspend operator fun invoke(id: String): DayRecordEntity = dayRecordRepository.getDayRecord(id)
}