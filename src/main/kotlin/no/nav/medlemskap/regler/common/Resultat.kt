package no.nav.medlemskap.regler.common

data class Resultat (
        val resultat: Resultattype,
        val beskrivelse: String,
        val underresultat: MutableList<Resultat> = mutableListOf()
) {

    companion object {
        fun avklar(metode: () -> Resultat): Resultat = metode.invoke()
    }

    infix fun hvisJa(metode: () -> Resultat?): Resultat {
        if (resultat == Resultattype.JA) {
            kallMetode(metode)
        }
        return this
    }

    infix fun hvisNei(metode: () -> Resultat?): Resultat {
        if (resultat == Resultattype.NEI) {
            kallMetode(metode)
        }
        return this
    }

    infix fun hvisUavklart(metode: () -> Resultat?): Resultat {
        if (resultat == Resultattype.UAVKLART) {
            kallMetode(metode)
        }
        return this
    }

    private fun kallMetode(metode: () -> Resultat?) {
        val resultat = metode.invoke()
        resultat?.let { underresultat.add(it) }
    }

}
