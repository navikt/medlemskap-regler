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

val enkelNorskMedArbeid = Datagrunnlag(
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
        arbeidsforhold = listOf(
                Arbeidsforhold(
                        arbeidsavtaler = listOf(
                                Arbeidsavtale(
                                        periode = Periode(LocalDate.now(), LocalDate.now()),
                                        yrkeskode = "0001",
                                        skipsregister = Skipsregister.nor,
                                        stillingsprosent = 25.0
                                        )
                        ),
                        arbeidsfolholdstype = Arbeidsforholdstype.MARITIM,
                        arbeidsgiver = Arbeidsgiver("Organisasjon", "1", "NOR"),
                        utenlandsopphold = listOf(),
                        periode = Periode(LocalDate.now(), LocalDate.now())
                )
        ),
        inntekt = listOf(),
        oppgaver = listOf(),
        dokument = listOf()
)

val enkelAmerikansk = Datagrunnlag(
        soknadsperiode = Periode(LocalDate.now(), LocalDate.now()),
        soknadstidspunkt = LocalDate.now(),
        brukerinput = Brukerinput(true),
        personhistorikk = Personhistorikk(
                statsborgerskap = listOf(Statsborgerskap("USA", LocalDate.now(), LocalDate.now())),
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
