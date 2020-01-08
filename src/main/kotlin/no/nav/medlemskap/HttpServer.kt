package no.nav.medlemskap

import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import no.nav.medlemskap.domene.Personhistorikk
import no.nav.medlemskap.domene.Regelavklaring
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

    routing {
        route("/") {
            post {
                val regelavklaring: Regelavklaring = call.receive()
                call.respond(bostedsvilkaar.evaluer(regelavklaring))
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
