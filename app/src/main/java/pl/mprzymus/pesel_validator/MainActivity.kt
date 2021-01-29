package pl.mprzymus.pesel_validator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import pl.mprzymus.pesel_validator.operations.ResultProvider
import pl.mprzymus.pesel_validator.ui.theme.AppTheme
import pl.mprzymus.pesel_validator.ui.theme.typography

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: PeselViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PeselViewModel::class.java)

        val provider = ResultProvider(
            getString(R.string.man),
            getString(R.string.woman),
            getString(R.string.validResultInfo),
            getString(R.string.invalidResultInfo),
            getString(R.string.dateOfBirth),
            viewModel
        )
        setContent {
            AppTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.padding(20.dp)
                ) {
                    LoadUi(header = getString(R.string.header), provider, viewModel)
                }
            }
        }
    }
}

@Composable
fun LoadUi(header: String, resultProvider: ResultProvider, viewModel: PeselViewModel) {
    val pesel = savedInstanceState { "" }
    Column {
        Box {
            Text(style = typography.h5, text = header, color = MaterialTheme.colors.primary )
        }
        PeselInput(pesel)
        ValidButton(resultProvider, pesel)

        Text(text = viewModel.isValid.value)
        Text(text = viewModel.dateOfBirth.value)
        Text(text = viewModel.sex.value)
    }
}

@Composable
private fun PeselInput(pesel: MutableState<String>) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = pesel.value,
        onValueChange = { pesel.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
private fun ValidButton(
    resultProvider: ResultProvider,
    pesel: MutableState<String>
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            resultProvider.setResultForPesel(pesel.value)
        }) {
        Text(text = "Waliduj")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.padding(10.dp)
        ) {
            //LoadUi(header = "Header")
        }
    }
}
