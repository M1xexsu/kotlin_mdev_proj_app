package github.m1xexsu.kotlin_mdev_proj_app.data.remote

import github.m1xexsu.kotlin_mdev_proj_app.data.model.arriveDTO
import github.m1xexsu.kotlin_mdev_proj_app.data.model.stationDTO
import github.mixexsu.application.dtos.userDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.Json

var serverlink = "http://10.0.2.2:8080/"

object KtorClient {
    var accessToken: String? = null
    private val client = HttpClient(OkHttp)
    {
        install(ContentNegotiation) {
            json(Json{
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        install(HttpTimeout)
        {
            requestTimeoutMillis = 5000
        }
        install(Auth)
        {
            bearer {
                loadTokens { accessToken?.let {
                        BearerTokens(
                            accessToken = it,
                            refreshToken = ""
                        )
                    }}
                sendWithoutRequest { request ->
                    request.url.build().encodedPath.startsWith("/admin")
                }
                sendWithoutRequest { request ->
                    request.url.build().encodedPath.startsWith("/auth/logout")
                }
            }
        }
    }

    suspend fun getstations(): List<stationDTO>{
        return client.get(serverlink + "arrive") {

        }.body<List<stationDTO>>()
    }

    suspend fun getarrive(
        _id: Int
    ): List<arriveDTO>{
        return client.get(serverlink + "arrive/" + _id.toString()).body<List<arriveDTO>>()
    }

    suspend fun getarrivesort(
        _id: Int,
        _dest: Int
    ): List<arriveDTO>
    {
        return client.get(serverlink + "arrive/$_id/$_dest").body<List<arriveDTO>>()
    }

    suspend fun login (
        username: String,
        password: String
    ): userDTO
    {
        return client.post(serverlink + "auth/login")
        {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            setBody(
                mapOf(
                    "username" to username,
                    "password" to password
                )
            )
        }.body<userDTO>()
    }



    suspend fun logout()
    {
        val ans =  client.post(serverlink + "auth/logout")
        {
            header(HttpHeaders.Authorization, "Bearer $accessToken")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        accessToken = null
        return ans.body()
    }



    suspend fun addbus(busname: String, startstation: Int, endstation: Int)
    {
        return client.post(serverlink + "admin/bus")
        {
            header(HttpHeaders.Authorization, "Bearer $accessToken")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            setBody(
                mapOf(
                    "bus_name" to busname,
                    "station_start" to startstation,
                    "station_end" to endstation
                )
            )
        }.body()
    }

    suspend fun addstation(stationname: String)
    {
        return client.post(serverlink + "admin/station")
        {
            header(HttpHeaders.Authorization, "Bearer $accessToken")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            setBody(
                mapOf(
                    "station_name" to stationname
                )
            )
        }.body()
    }

    suspend fun addarrive(bus_id: Int, station_id: Int, load: Int, arrive_time: LocalDateTime)
    {
        return client.post(serverlink + "admin/arrive")
        {
            header(HttpHeaders.Authorization, "Bearer $accessToken")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            setBody(
                mapOf(
                    "bus_id" to bus_id,
                    "station_id" to station_id,
                    "load" to load,
                    "arrive_time" to arrive_time
                )
            )
        }.body()
    }
}