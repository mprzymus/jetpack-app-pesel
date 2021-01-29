package pl.mprzymus.pesel_validator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PeselViewModel: ViewModel() {
    var sex = mutableStateOf("")
    var isValid = mutableStateOf("")
    var dateOfBirth = mutableStateOf("")

    fun onSexChanged(sex: String) {
        this.sex.value = sex
    }

    fun onIsValidChanged(isValid: String) {
        this.isValid.value = isValid
    }

    fun onBirthDateChanged(dateOfBirth: String) {
        this.dateOfBirth.value = dateOfBirth
    }
}