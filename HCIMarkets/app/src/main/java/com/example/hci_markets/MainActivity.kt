package com.example.hci_markets

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hci_markets.presentation.screen.TermsAndConditionsScreen
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import com.example.hci_markets.util.PrefKeys

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences(PrefKeys.APP_PREFERENCES, MODE_PRIVATE)
        val termsAccepted = prefs.getBoolean(PrefKeys.TERMS_ACCEPTED, false)

        if(termsAccepted){
            Log.i("Main","Terms accepted already")
        }

        setContent {
            HCIMarketsTheme {
                TermsAndConditionsScreen(
                    onAccept = {
                               prefs.edit()
                                   .putBoolean(PrefKeys.TERMS_ACCEPTED, true)
                                   .apply()
                    },
                    onDecline = {
                        Toast.makeText(
                            this@MainActivity,
                            R.string.terms_disclaimer,
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                )
            }
        }
    }
}