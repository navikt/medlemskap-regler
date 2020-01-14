package no.nav.medlemskap.regler

class OgRegelbeskrivelse<T, U> : Regelbeskrivelse<Pair<T, U>> {
    constructor(regel1: Regelbeskrivelse<T>, regel2: Regelbeskrivelse<U>) {
        super(identifikator = "", beskrivelse = regel1.beskrivelse + regel2.beskrivelse)
    }



    fun<T, U> og(regel1: Regelbeskrivelse<T>, regel2: Regelbeskrivelse<U>): Boolean? {
        val res1 = regel1.operasjon.invoke()
        val res2 = regel2.operasjon.invoke()
    }
}
