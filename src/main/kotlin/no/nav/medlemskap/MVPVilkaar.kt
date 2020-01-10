package no.nav.medlemskap

import no.nav.medlemskap.domene.Regelavklaring
import no.nav.nare.core.evaluations.Evaluering
import no.nav.nare.core.specifications.Spesifikasjon

val mvpVilkaar = Spesifikasjon<Regelavklaring>(
        identitet = "§ 2.1",
        beskrivelse = "Personer som er bosatt i Norge, er pliktige medlemmer i folketrygden.",
        children = listOf(
                statsborgerskapVilkaar, utvandretVilkaar, norskeAdresserVilkaar
        ),
        implementasjon = { alleVilkaarOppfyllt(it) }
)

fun alleVilkaarOppfyllt(regelavklaring: Regelavklaring): Evaluering =
    statsborgerskapVilkaar.og(utvandretVilkaar).og(norskeAdresserVilkaar).evaluer(regelavklaring)
