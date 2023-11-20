package com.nane.storage.presentation.data

@Retention(AnnotationRetention.SOURCE)
annotation class StorageViewType {
    companion object {
        const val STORAGE_WISH_TYPE = "wish"
        const val STORAGE_HAVING_TYPE = "having"
        const val STORAGE_WISH_TEXT = "위시리스트"
        const val STORAGE_HAVING_TEXT = "보유리스트"

        const val STORAGE_WISH_POSITION = 0
        const val STORAGE_HAVING_POSITION = 1
    }
}