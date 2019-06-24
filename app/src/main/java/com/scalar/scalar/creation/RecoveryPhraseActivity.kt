package com.scalar.scalar.creation

import android.os.Bundle
import android.widget.FrameLayout
import com.scalar.scalar.BaseActivity
import com.scalar.scalar.R

class RecoveryPhraseActivity : BaseActivity(), RecoveryPhraseIntroFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery_phrase)

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById<FrameLayout>(R.id.fragment_container) != null) {

            // If we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return
            }

            // Create a new Fragment to be placed in the activity layout
            val introFragment = RecoveryPhraseIntroFragment.newInstance()

            // Add the fragment to the 'fragment_container' FrameLayout
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, introFragment).commit()
        }
    }

    override fun onButtonClicked() {
        // TODO: Actually add the PhraseFragment
        // Create a new Fragment to be placed in the activity layout
        val phraseFragment = RecoveryPhraseIntroFragment.newInstance()

        // Add the fragment to the 'fragment_container' FrameLayout
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, phraseFragment).commit()
    }

}
