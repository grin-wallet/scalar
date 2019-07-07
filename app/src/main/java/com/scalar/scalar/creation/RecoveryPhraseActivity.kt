package com.scalar.scalar.creation

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.scalar.scalar.BaseActivity
import com.scalar.scalar.R
import com.scalar.scalar.home.HomeActivity

class RecoveryPhraseActivity : BaseActivity(),
        RecoveryPhraseIntroFragment.OnPhraseIntroInteractionListener,
        PhraseFragment.OnPhraseFragmentInteractionListener,
        CreateReadyFragment.OnCreateReadyFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery_phrase)

        if (findViewById<FrameLayout>(R.id.fragment_container) != null) {

            // If we're being restored from a previous state, then we don't need to do anything
            // and should return or else we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return
            }

            val introFragment = RecoveryPhraseIntroFragment.newInstance()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, introFragment).commit()
        }
    }

    override fun onButtonClicked() {
        val phraseFragment = PhraseFragment.newInstance()

        // Add the fragment to the 'fragment_container' FrameLayout
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, phraseFragment)
                .addToBackStack(PhraseFragment.TAG)
                .commit()
    }

    override fun onPhraseButtonClicked() {
        val createReadyFragment = CreateReadyFragment.newInstance()

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, createReadyFragment)
                .addToBackStack(CreateReadyFragment.TAG)
                .commit()
    }

    override fun onCreateReadyFragmentInteraction() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

}
