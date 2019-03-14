package com.gora.androidapp.view

import com.gora.androidapp.model.messageDataResponse

interface MainActivityView {
    fun showMessage(data: List<messageDataResponse>)
}