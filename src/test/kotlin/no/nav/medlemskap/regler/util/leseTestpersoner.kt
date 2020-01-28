package no.nav.medlemskap.regler.util

import no.nav.medlemskap.domene.Datagrunnlag
import no.nav.medlemskap.objectMapper

class TestpersonLeser {
    companion object {
        fun lesNorskeDatagrunnlagFraFil(filnavn: String): Datagrunnlag {
            val content = TestpersonLeser::class.java.getResource("/testpersoner/norske/$filnavn.json")
            return objectMapper.readValue(content, Datagrunnlag::class.java)
        }

        fun lesUtenlandskeDatagrunnlagFraFil(filnavn: String): Datagrunnlag {
            val content = TestpersonLeser::class.java.getResource("/testpersoner/utenlandske/$filnavn.json")
            return objectMapper.readValue(content, Datagrunnlag::class.java)
        }
    }
}
