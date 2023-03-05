package com.bitflyer.ghc.data.datasources

import android.util.Log
import com.bitflyer.ghc.core.errors.Failure
import com.bitflyer.ghc.core.utils.Either
import com.bitflyer.ghc.core.utils.Left
import com.bitflyer.ghc.core.utils.Right
import com.bitflyer.ghc.domain.models.Event
import com.bitflyer.ghc.domain.models.User
import com.bitflyer.ghc.domain.models.UserDetails
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class GitHubDataSource {
    companion object {
        private val okHttpClient = OkHttpClient.Builder().addInterceptor {
            // TODO: Add GitHub personal access token here.
//            val request =
//                it.request().newBuilder().addHeader(
//                    "Authorization",
//                    "Bearer <ADD PAT HERE>"
//                ).build()

            it.proceed(request)
        }.build()

        private val client: GitHubApi = Retrofit.Builder().baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GitHubApi::class.java)
    }

    suspend fun <T> request(execute: suspend (c: GitHubApi) -> Response<T>): Either<T> {
        val res: Either<T> = try {
            val response: Response<T> = execute(client)

            Log.d("GHC::${javaClass.simpleName}", response.toString())

            if (response.isSuccessful) Right<T>(response.body()!!) else Left<T>(
                Failure(
                    response.message(),
                    response.code()
                )
            )
        } catch (e: Exception) {
            Log.e("GHC::${javaClass.name}", e.message ?: "Exception")
            Left<T>()
        }

        return res
    }
}

interface GitHubApi {
    @GET("users")
    suspend fun getAllUsers(
        @Query(value = "since") fromId: Int,
        @Query(value = "per_page") limit: Int,
    ): Response<List<User>>

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): Response<UserDetails>

    @GET("users/{username}/events/public")
    suspend fun getUserEvents(
        @Path("username") username: String,
        @Query(value = "page") page: Int,
        @Query(value = "per_page") limit: Int
    ): Response<List<Event>>
}