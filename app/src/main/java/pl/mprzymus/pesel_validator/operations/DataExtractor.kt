package pl.mprzymus.pesel_validator.operations

import pl.mprzymus.pesel_validator.R
import java.time.LocalDate

class DataExtractor {
    companion object {
        const val MAX_1900s_MONTH = 12
    }
    fun extractDate(pesel: String): LocalDate {
        val year = pesel.substring(0,2)
        val peselMonth = pesel.substring(2,4)
        val day = pesel.substring(4,6)
        val is2000s = peselMonth.toInt() > MAX_1900s_MONTH
        val yearBeginning = if (is2000s) "20" else "19"
        val firstMonthDig = if (is2000s) peselMonth.substring(0,1).toInt() - 2 else peselMonth.substring(0,1)
        val month = "$firstMonthDig${peselMonth.substring(1,2)}"
        return LocalDate.of("$yearBeginning$year".toInt(), month.toInt(), day.toInt())
    }

    fun extractSex(pesel: String, male: String, female: String): String {
        val lastChar = pesel.last().toInt()
        return if (lastChar  % 2 == 1) male else female
    }
}