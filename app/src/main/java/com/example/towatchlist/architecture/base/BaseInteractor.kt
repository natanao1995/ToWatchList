package com.example.towatchlist.architecture.base

import com.example.towatchlist.model.remote.entity.ErrorResponseEntity
import com.google.gson.Gson
import retrofit2.Response
import java.lang.Exception

open class BaseInteractor {
    suspend fun <T : Any> processRequest(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call.invoke()

            if (response.isSuccessful)
                Result.Success(response.body()!!)
            else {
                handleErrorResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private fun handleErrorResponse(responseString: String?): Result.Error {
        val errorEntity = Gson().fromJson(responseString, ErrorResponseEntity::class.java)
        return Result.Error(Exception(errorEntity?.statusMessage))
    }
}