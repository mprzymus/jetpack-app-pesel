package pl.mprzymus.pesel_validator.operations

import pl.mprzymus.pesel_validator.PeselViewModel

class ResultProvider(
    private val male: String,
    private val female: String,
    private val validInfo: String,
    private val invalidInfo: String,
    private val dateOfBirth: String,
    private val viewModel: PeselViewModel
) {
    private val validator = Validator()
    private val dataExtractor = DataExtractor()
    fun setResultForPesel(pesel: String) {
        if (validator.isValid(pesel)) {
            val date = dataExtractor.extractDate(pesel)
            viewModel.onBirthDateChanged("$dateOfBirth $date")
            viewModel.onSexChanged(dataExtractor.extractSex(pesel, male, female))
            viewModel.onIsValidChanged(validInfo)
        }
        else {
            viewModel.onBirthDateChanged("")
            viewModel.onSexChanged("")
            viewModel.onIsValidChanged(invalidInfo)
        }
    }
}