package com.caldeirasoft.outcast.domain.util

import kotlinx.coroutines.flow.*

/**
 * A generic class that can provide a resource backed by both
 * the SQLite database and the network.
 *
 * [ResultType] represents the type for database.
 * [RequestType] represents the type for network.
 */
inline fun <ResponseType: Any> networkCall(
    crossinline fetchFromRemote: suspend () -> NetworkResponse<ResponseType>,
    crossinline onFetchFailed: (errorBody: String?, statusCode: Int) -> Unit = { _, _ -> Unit }
) = flow<Resource<ResponseType>> {

    emit(Resource.Loading(null))

    when (val response = fetchFromRemote())
    {
        is NetworkResponse.Success -> {
            emit(Resource.Success(response.body))
        }
        is NetworkResponse.NetworkError -> emit(Resource.Error<ResponseType>(response.error.message ?: "Network error"))
        is NetworkResponse.ServerError -> emit(Resource.Error<ResponseType>(response.code.toString()))
        is NetworkResponse.UnknownError -> emit(Resource.ErrorThrowable<ResponseType>(response.error))
    }
}

/**
 * A generic function that can stream network resource and fetch and save it
 * It is used for retry resource when we already observe data from database
 *
 * [ResultType] represents the type for database.
 * [RequestType] represents the type for network.
 */
inline fun <ResponseType: Any> fetchResourceAndCache(
    crossinline networkCall: suspend () -> NetworkResponse<ResponseType>,
    crossinline saveRemoteData: suspend (ResponseType) -> Unit = { Unit },
) = flow<Resource<Unit>> {

    emit(Resource.Loading(null))
    when (val response = networkCall.invoke()) {
        is NetworkResponse.Success -> {
            saveRemoteData(response.body)
            emit(Resource.Success(Unit))
        }
        is NetworkResponse.Cached -> emit(Resource.Success(Unit))
        is NetworkResponse.ServerError -> emit(Resource.Error<Unit>(response.code.toString() ?: "Server error"))
        is NetworkResponse.UnknownError -> emit(Resource.ErrorThrowable<Unit>(response.error))
    }
}
