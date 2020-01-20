package no.nav.medlemskap.v1

import com.google.gson.GsonBuilder
import no.nav.medlemskap.domene.Periode
import no.nav.medlemskap.domene.Personhistorikk
import no.nav.medlemskap.domene.Regelavklaring
import no.nav.medlemskap.v1.AvklaringerEos.avklarOmPersonErOmfattetAvEosavtalen
import no.nav.medlemskap.v1.AvklaringerKapittel2.avklarOmPersonErMedlemIhhtKapittel2
import no.nav.medlemskap.v1.AvklaringerLovvalg.avklarOmLovvalgErNorsk
import no.nav.medlemskap.v1.AvklaringerMedl.avklarOmPersonHarTidligereVedtak
import no.nav.medlemskap.v1.AvklaringerTrygdeavtaler.avklarOmPersonErOmfattetAvNorskTrygdeavtale
import java.time.LocalDate

fun avklarMedlemskap(ra: Regelavklaring): Resultat {

    val hovedregel =
            avklarOmPersonHarTidligereVedtak.eller(avklarOmPersonErMedlemIhhtKapittel2)
                    .hvisNei(avklarOmPersonErOmfattetAvEosavtalen
                            .hvisJa(avklarOmLovvalgErNorsk
                                    .hvisJa(avklarOmPersonErMedlemIhhtKapittel2))
                            .hvisNei(avklarOmPersonErOmfattetAvNorskTrygdeavtale))

    return hovedregel.evaluer(ra)

}

fun main() {
    val ra = Regelavklaring(
            soknadsperiode = Periode(LocalDate.now(), LocalDate.now()),
            soknadstidspunkt = LocalDate.now(),
            personhistorikk = Personhistorikk(
                    statsborgerskap = listOf(),
                    personstatuser = listOf(),
                    bostedsadresser = listOf(),
                    postadresser = listOf(),
                    midlertidigAdresser = listOf()
            )
    )

    val gson = GsonBuilder().setPrettyPrinting().create()

    println(gson.toJson(avklarMedlemskap(ra)))
}
