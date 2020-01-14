package no.nav.medlemskap.regler

open class Regelbeskrivelse<T> (
        val identifikator: String,
        val beskrivelse: String,
        val operasjon: (t: T) -> Boolean?
) {
    fun evaluer(t: T): Boolean? = operasjon.invoke(t)
}
