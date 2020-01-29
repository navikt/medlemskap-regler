package no.nav.medlemskap

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.AutoHeadResponse
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.jackson.JacksonConverter
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
import no.nav.medlemskap.regler.common.Fakta.Companion.initialiserFakta
import no.nav.medlemskap.regler.v1.RegelsettForMedlemskap

val objectMapper: ObjectMapper = ObjectMapper()
        .registerKotlinModule()
        .registerModule(JavaTimeModule())
        .configure(SerializationFeature.INDENT_OUTPUT, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)


fun createHttpServer(): ApplicationEngine = embeddedServer(Netty, 7070) {

    install(ContentNegotiation) {
        register(ContentType.Application.Json, JacksonConverter(objectMapper))
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
                val datagrunnlag: Datagrunnlag = call.receive()
                call.respond(RegelsettForMedlemskap(initialiserFakta(datagrunnlag)).evaluer())
            }
        }
        route("/v1") {
            post {
                val datagrunnlag: Datagrunnlag = call.receive()
                call.respond(RegelsettForMedlemskap(initialiserFakta(datagrunnlag)).evaluer())
            }
        }
        route("/versions") {
            get {
                call.respond(hentVersjoner())
            }
        }
        route("/isAlive") {
            get {
                call.respond("Ok")
            }
        }
        route("/isReady") {
            get {
                call.respond("Ok")
            }
        }
    }
}
