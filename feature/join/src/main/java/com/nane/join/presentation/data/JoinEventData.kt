package com.nane.join.presentation.data

/**
 * Created by haul on 11/20/23
 */
sealed class JoinActEventData {
    object MoveNextStep : JoinActEventData()

    data class ChangeProgressView(val progress: Int) : JoinActEventData()
}


sealed class JoinNickNameEventData {
    data class VerifyResult(val isDuplicate: Boolean) : JoinNickNameEventData()
}


sealed class JoinPasswordEventData {
    data class ShowErrorView(val errorText: String?) : JoinPasswordEventData()
    data class EnableNextBtn(val isEnable: Boolean) : JoinPasswordEventData()
}


sealed class JoinEmailAuthEventData {
    data class SendEmailAuthResult(val isSuccess: Boolean) : JoinEmailAuthEventData()
    data class VerifyCheck(val isSuccessVerify: Boolean) : JoinEmailAuthEventData()
}


sealed class JoinSelectAccordEventData {

}
