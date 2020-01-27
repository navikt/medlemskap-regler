package no.nav.medlemskap.regler.v1

import no.nav.medlemskap.domene.Journalpost
import no.nav.medlemskap.domene.Oppgave
import no.nav.medlemskap.domene.Status
import no.nav.medlemskap.regler.common.*
import no.nav.medlemskap.regler.common.HvisUttrykk.Companion.hvis
import no.nav.medlemskap.regler.common.Resultat.Companion.avklar
import no.nav.medlemskap.regler.common.Funksjoner.antall
import no.nav.medlemskap.regler.common.Funksjoner.inneholder
import no.nav.medlemskap.regler.common.Funksjoner.ja
import no.nav.medlemskap.regler.common.Funksjoner.nei
import no.nav.medlemskap.regler.common.Funksjoner.uavklart

class RegelsettForVedtak(fakta: Fakta) : Regelsett("Regelsett for vedtak", fakta) {

    override fun evaluer(): Resultat {
        val resultat =
                avklar {
                    harAvklarteVedtakIMedl evaluerMed fakta
                } hvisNei {
                    avklar {
                        finnesDetÅpenOppgaveIGsak evaluerMed fakta
                    } hvisNei {
                        avklar {
                            finnesDetDokumenterIJoark evaluerMed fakta
                        } hvisNei {
                            konkluderMed(nei("Personen har ingen manuelle vedtak"))
                        } hvisJa {
                            konkluderMed(uavklart("Personen har dokumenter i JOARK"))
                        }
                    } hvisJa {
                        konkluderMed(uavklart("Personen har dokumenter i GOSYS"))
                    }
                } hvisJa {
                    konkluderMed(uavklart("Personen har vedtak i MEDL"))
                }

        return hentUtKonklusjon(resultat)
    }

    private val tillatteTemaer = listOf("MED", "UFM", "TRY")
    private val tillatteStatuser = listOf(Status.AAPNET, Status.OPPRETTET, Status.UNDER_BEHANDLING)

    private val harAvklarteVedtakIMedl = Avklaring(
            identifikator = "1",
            avklaring = "Sjekk om det finnes avklarte vedtak i MEDL",
            beskrivelse = "",
            operasjon = { sjekkPerioderIMedl(it) }
    )

    private val finnesDetDokumenterIJoark = Avklaring(
            identifikator = "3",
            avklaring = "Finnes det åpne dokumenter i JOARK",
            beskrivelse = "",
            operasjon = { tellDokumenter(it) }
    )

    private val finnesDetÅpenOppgaveIGsak = Avklaring(
            identifikator = "2",
            avklaring = "Finnes det åpne oppgaver i GOSYS",
            beskrivelse = "",
            operasjon = { tellÅpneOppgaver(it) }
    )

    private fun sjekkPerioderIMedl(fakta: Fakta): Resultat =
            hvis {
                antall(fakta.personensPerioderIMedl()) == 0
            } så {
                nei("Personen har ingen vedtak i MEDL")
            } ellers {
                ja("Personen har vedtak i MEDL")
            }

    private fun tellDokumenter(fakta: Fakta): Resultat =
            hvis {
                antallDokumenter(fakta.personensDokumenterIJoark()) > 0
            } så {
                ja("Personen har dokumenter knyttet til medlemskapsaker.")
            } ellers {
                nei("Personen har ingen dokumenter knyttet til medlemskapsaker.")
            }


    private fun tellÅpneOppgaver(fakta: Fakta): Resultat =
            hvis {
                antallÅpneOppgaver(fakta.personensOppgaverIGsak()) > 0
            } så {
                ja("Personen har åpne oppgaver i GOSYS.")
            } ellers {
                nei("Personen har ingen åpne oppgaver i GOSYS.")
            }


    private fun antallDokumenter(liste: List<Journalpost>) =
            liste
                    .count { journalpost -> tillatteTemaer inneholder journalpost.tema }

    private fun antallÅpneOppgaver(liste: List<Oppgave>) =
            liste
                    .count { oppgave -> tillatteTemaer inneholder oppgave.tema && tillatteStatuser inneholder oppgave.status }


}
