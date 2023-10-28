package com.nane.join.presentation.data

/**
 * Created by haul on 10/28/23
 */
sealed class JoinActEventData {
    object MoveNextStep : JoinActEventData()

    data class ChangeProgressView(val progress: Int) : JoinActEventData()
}
