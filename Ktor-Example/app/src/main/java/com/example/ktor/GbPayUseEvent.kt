package com.example.ktor

sealed class GbPayUseEvent

class ToastEvent(val toastData: ToastData): GbPayUseEvent()
object ShowManageActivityEvent: GbPayUseEvent()
class MoveToPageEvent(val page: Int): GbPayUseEvent()
object NfcSettingsPopupEvent: GbPayUseEvent()
object HceEvent: GbPayUseEvent()
class StartNFCServiceEvent(val event: String): GbPayUseEvent()
object FinishEvent: GbPayUseEvent()