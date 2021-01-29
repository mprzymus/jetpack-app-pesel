package pl.mprzymus.pesel_validator.operations

class Validator {
    companion object {
        const val MAX_MONTH = 32
        const val MAX_DAY = 31
        const val PESEL_LENGTH = 11
    }

    fun isValid(pesel: String): Boolean {
        return try {
            val asInts = toList(pesel)
            hasValidLength(pesel) && isMonthValid(asInts) &&
                    isDayValid(asInts) && isCheckSumValid(asInts)
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun hasValidLength(pesel: String) = pesel.length == PESEL_LENGTH

    private fun isCheckSumValid(asInts: List<Int>): Boolean {
        val sum = asInts[0] + asInts[1] * 3 + asInts[2] * 7 + asInts[3] * 9 +
                asInts[4] + asInts[5] * 3 + asInts[6] * 7 + asInts[7] * 9 +
                asInts[8] + asInts[9] * 3 + asInts[10]
        return (sum % 10) == 0
    }

    private fun isDayValid(asInts: List<Int>) =
        !isLower(asInts[5], asInts[4], MAX_DAY)

    private fun isMonthValid(asInts: List<Int>) =
        !isLower(asInts[3], asInts[2], MAX_MONTH)

    private fun isLower(tensDigit: Int, unitDigit: Int, maxValue: Int): Boolean {
        return tensDigit * 10 + unitDigit <= maxValue
    }

    private fun toList(pesel: String): List<Int> {
        return pesel.asSequence().map { char -> char.toString().toInt() }.toList()
    }
}