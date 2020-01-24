package no.nav.medlemskap.regler.common

data class Resultat (
        val resultat: Resultattype,
        val beskrivelse: String,
        val underresultat: MutableList<Resultat> = mutableListOf()
) {

    companion object {
        fun avklar(metode: () -> Resultat): Resultat = metode.invoke()
    }

    fun hvisJa(metode: () -> Resultat): Resultat {
        if (resultat == Resultattype.JA) {
            underresultat.add(metode.invoke())
        }
        return this
    }

    fun hvisNei(metode: () -> Resultat): Resultat {
        if (resultat == Resultattype.NEI) {
            underresultat.add(metode.invoke())
        }
        return this
    }

    fun hvisUavklart(metode: () -> Resultat): Resultat {
        if (resultat == Resultattype.UAVKLART) {
            underresultat.add(metode.invoke())
        }
        return this
    }

}
