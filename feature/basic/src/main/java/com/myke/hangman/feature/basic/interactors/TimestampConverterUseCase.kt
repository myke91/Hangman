package com.myke.hangman.feature.basic.interactors

import android.text.format.DateFormat
import com.myke.hangman.feature.basic.interactors.types.BaseUseCaseWithParams
import java.util.*

class TimestampConverterUseCase: BaseUseCaseWithParams<Long, String> {

    override suspend fun run(time: Long): String {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = time
        return DateFormat.format("dd MMMM yyyy HH:mm:ss", calendar).toString()
    }
}