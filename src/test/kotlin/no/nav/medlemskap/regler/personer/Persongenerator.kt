package no.nav.medlemskap.regler.personer

import no.nav.medlemskap.domene.*
import java.time.LocalDate

val enkelNorsk = Datagrunnlag(
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
        medlemskapsunntak = listOf(),
        arbeidsforhold = listOf(),
        inntekt = listOf(),
        oppgaver = listOf(),
        dokument = listOf()
)


