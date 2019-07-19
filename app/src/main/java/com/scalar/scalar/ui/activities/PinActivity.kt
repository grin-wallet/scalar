package com.scalar.scalar.ui.activities


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.scalar.scalar.R
import com.scalar.scalar.interfaces.OnPinLockCompleteListener
import kotlinx.android.synthetic.main.activity_pin.*

class PinActivity : AppCompatActivity() {

    private var PIN : String? = "1234"
    private lateinit var appContext : Context

    companion object {
        private const val INTENT_ARG_MESSAGE: String = "INTENT_ARG_MESSAGE"
        private const val INTENT_ARG_PIN: String = "INTENT_ARG_PIN"

        /**
         * New Instance of Intent to launch a {@link PinActivity}
         * @param context the activityContext of the requestor
         * @param pin pin to verified otherwise it will simple return the inserted pin, it must contain 4 number characters.
         * @param message message to show on the top of the pinlock.
         */
        fun newInstance(context: Context, pin : String?): Intent {
            val intent = Intent(context, PinActivity::class.java)
            if (pin != null) {
                if (pin.length == 4) {
                    pin.toCharArray().forEach {
                        if (!it.isDigit()) throw IllegalStateException("Character (Unicode code point) has to be a digit, found ='$it'.")
                    }
                    intent.putExtra(INTENT_ARG_PIN, pin)
                } else {
                    throw IllegalStateException("pin ahs to contain 4 characters, found = '${pin.length}")
                }
            }
            return intent
        }

        fun getPinFromIntent(intent:Intent?) : String? {
            if(intent == null) return null
            return intent.getStringExtra(INTENT_ARG_PIN)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        appContext = applicationContext

        pinLockView.setPinLockListener(object : OnPinLockCompleteListener() {
            override fun onComplete(pin: String?) {
                if (PIN == null || PIN == pin) {
                    val intent = Intent()
                    intent.putExtra(INTENT_ARG_PIN, pin)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    processIncorrectPin()
                }
            }
        })

        pinLockView.attachIndicatorDots(indicatorDots)

//        if (!intent.hasExtra(INTENT_ARG_MESSAGE)) throw IllegalStateException("missing argument {$INTENT_ARG_MESSAGE}, did you use #newInstance(..)?")

//        PIN = intent.getStringExtra(INTENT_ARG_PIN)
    }

    //region User Interface

    private fun processIncorrectPin() {
        showWrongPinDots(true)
        val shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake)
        shakeAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {}
            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                showWrongPinDots(false)
                pinLockView.resetPinLockView()
            }
        })
        wrongPinDots.startAnimation(shakeAnimation)
    }

    private fun showWrongPinDots(show: Boolean) {
        indicatorDots.visibility = if (show) View.GONE else View.VISIBLE
        wrongPinDots.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }
    //endregion
}