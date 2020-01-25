package no.nav.medlemskap.regler.common

object Funksjoner {

    fun ja(begrunnelse: String): Resultat = Resultat(Resultattype.JA, begrunnelse)

    fun nei(begrunnelse: String): Resultat = Resultat(Resultattype.NEI, begrunnelse)

    fun uavklart(begrunnelse: String): Resultat = Resultat(Resultattype.UAVKLART, begrunnelse)

    fun antall(liste: List<Any>): Number = liste.size

    infix fun List<Any>.inneholder(objekt: Any?) = this.contains(objekt)

}
