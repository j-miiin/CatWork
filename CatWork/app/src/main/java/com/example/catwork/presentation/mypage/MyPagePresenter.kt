package com.example.catwork.presentation.mypage

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class MyPagePresenter: MyPageContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        
    }

    override fun onDestroyView() {}

    override fun fetchCalendarRecord(date: String) {

    }
}