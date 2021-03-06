package com.mercadopago.android.px.internal.core

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class FlowIdInterceptor(val flowIdProvider: FlowIdProvider) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .header(FLOW_ID_HEADER, flowIdProvider.flowId)
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val FLOW_ID_HEADER = "x-flow-id"
    }
}