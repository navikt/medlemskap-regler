package no.nav.medlemskap

import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.AutoHeadResponse
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import no.nav.medlemskap.domene.Datagrunnlag
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

val localDateSerializer: JsonSerializer<LocalDate> = JsonSerializer { src, _, _ ->
    if (src == null) null else JsonPrimitive(src.format(formatter))
}

val localDateDeserializer: JsonDeserializer<LocalDate> = JsonDeserializer<LocalDate> { json, _, _ ->
    if (json == null) null else LocalDate.parse(json.asString, formatter)
}

fun createHttpServer(): ApplicationEngine = embeddedServer(Netty, 7070) {
    install(ContentNegotiation) {
        gson {
            registerTypeAdapter(LocalDate::class.java, localDateDeserializer)
            registerTypeAdapter(LocalDate::class.java, localDateSerializer)
            setPrettyPrinting()
            disableHtmlEscaping()

        }
    }

    install(AutoHeadResponse)

    install(CORS) {
        method(HttpMethod.Post)
        method(HttpMethod.Options)
        header(HttpHeaders.ContentType)
        host(host = "localhost:3000", schemes = listOf("http", "https"))
        allowSameOrigin = true
    }


    routing {
        route("/") {
            post {
                val regelavklaring: Datagrunnlag = call.receive()
                call.respond(Regelkjøring(regelavklaring).regelkjøring())
            }
        }
        route("/v1") {
            post {
                val regelavklaring: Datagrunnlag = call.receive()
                call.respond(Regelkjøring(regelavklaring).regelkjøring())
            }
        }
        route("/versions") {
            get {
                call.respond(hentVersjoner())
            }
        }
        route( "/isAlive"){
            get{
               call.respond("Ok")
            }
        }
        route("/isReady"){
            get{
                call.respond("Ok")
            }
        }
    }
}
