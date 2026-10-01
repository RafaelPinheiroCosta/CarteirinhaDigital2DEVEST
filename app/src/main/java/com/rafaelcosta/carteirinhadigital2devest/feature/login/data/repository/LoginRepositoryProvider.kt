package com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository

import com.rafaelcosta.carteirinhadigital2devest.feature.login.data.remote.network.NetworkFactory


object LoginRepositoryProvider {
    private const val USE_FAKE_REPOSITORY = false

    fun provide(): LoginRepository {
        return if (USE_FAKE_REPOSITORY) {
            FakeLoginRepositoryImpl()
        } else {
            ApiLoginRepositoryImpl(NetworkFactory.createAuthApi())
        }
    }
}