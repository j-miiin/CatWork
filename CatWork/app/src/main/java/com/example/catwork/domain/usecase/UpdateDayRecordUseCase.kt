package com.example.catwork.domain.usecase

import com.example.catwork.data.entity.DayRecordEntity
import com.example.catwork.data.repository.DayRecordRepository

class UpdateDayRecordUseCase(private val dayRecordRepository: DayRecordRepository) {

    suspend operator fun invoke(dayRecordEntity: DayRecordEntity) {
        return dayRecordRepository.updateDayRecord(dayRecordEntity)
    }
}