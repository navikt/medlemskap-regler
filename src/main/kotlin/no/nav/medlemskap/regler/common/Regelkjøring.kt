package no.nav.medlemskap.regler.common

import com.google.gson.GsonBuilder
import no.nav.medlemskap.domene.*
import no.nav.medlemskap.regler.common.Fakta.Companion.initialiserFakta
import no.nav.medlemskap.regler.v1.RegelsettForMedlemskap
import java.time.LocalDate

class Regelkjøring(datagrunnlag: Datagrunnlag) {

    private val fakta = initialiserFakta(datagrunnlag)

    fun regelkjøring(): Resultat = RegelsettForMedlemskap(fakta).evaluer()

}

fun main() {
    val datagrunnlag = Datagrunnlag(
            soknadsperiode = Periode(LocalDate.now(), LocalDate.now()),
            soknadstidspunkt = LocalDate.now(),
            brukerinput = Brukerinput(false),
            personhistorikk = Personhistorikk(
                    statsborgerskap = listOf(Statsborgerskap("NOR", LocalDate.now(), LocalDate.now())),
                    personstatuser = listOf(),
                    bostedsadresser = listOf(),
                    postadresser = listOf(),
                    midlertidigAdresser = listOf()
            ),
            arbeidsforhold = listOf(
                    Arbeidsforhold(
                            periode = Periode(LocalDate.now(), LocalDate.now()),
                            utenlandsopphold = listOf(),
                            arbeidsgiver = Arbeidsgiver(
                                    type = "",
                                    identifikator = "",
                                    landkode = "NOR"
                            ),
                            arbeidsfolholdstype = Arbeidsforholdstype.MARITIM,
                            arbeidsavtaler = listOf(
                                    Arbeidsavtale(
                                            periode = Periode(LocalDate.now(), LocalDate.now()),
                                            yrkeskode = "",
                                            skipsregister = Skipsregister.nor,
                                            stillingsprosent = 25.0
                                    )
                            )
                    )
            )
    )

    val gson = GsonBuilder().setPrettyPrinting().create()

    println(gson.toJson(Regelkjøring(datagrunnlag).regelkjøring()))
}
