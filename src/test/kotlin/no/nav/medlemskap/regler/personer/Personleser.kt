package no.nav.medlemskap.regler.personer

import no.nav.medlemskap.domene.Datagrunnlag
import no.nav.medlemskap.objectMapper

class Personleser {

    private val norsk = "/testpersoner/norske"
    private val ikkeEøs = "/testpersoner/ikke_eos"

    fun enkelNorsk() = lesDatagrunnlag("$norsk/kun_enkelt_statsborgerskap.json")
    fun enkelNorskArbeid() = lesDatagrunnlag("$norsk/enkelt_statsborgerskap_en_norsk_jobb.json")

    fun enkelAmerikansk() = lesDatagrunnlag("$ikkeEøs/kun_enkelt_amerikansk_statsborgerskap.json")
    fun amerikanskMedl() = lesDatagrunnlag("$ikkeEøs/amerikansk_med_vedtak_i_medl.json")
    fun amerikanskGosys() = lesDatagrunnlag("$ikkeEøs/amerikansk_med_oppgave_i_gosys.json")
    fun amerikanskJoark() = lesDatagrunnlag("$ikkeEøs/amerikansk_med_dokument_i_joark.json")

    private fun lesDatagrunnlag(filnavn: String): Datagrunnlag {
        val res = Personleser::class.java.getResource(filnavn)
        return objectMapper.readValue(res, Datagrunnlag::class.java)
    }

}
