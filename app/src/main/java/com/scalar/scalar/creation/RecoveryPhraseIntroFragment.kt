package com.scalar.scalar.creation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.scalar.scalar.R
import kotlinx.android.synthetic.main.fragment_recovery_phrase_intro.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RecoveryPhraseIntroFragment.OnPhraseIntroInteractionListener] interface
 * to handle interaction events.
 * Use the [RecoveryPhraseIntroFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RecoveryPhraseIntroFragment : Fragment() {
    private var listener: OnPhraseIntroInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_recovery_phrase_intro, container, false)

        rootView.startRecoveryPhraseButton.setOnClickListener {
            listener?.onButtonClicked()
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPhraseIntroInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnPhraseIntroInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnPhraseIntroInteractionListener {
        fun onButtonClicked()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment.
         *
         * @return A new instance of fragment RecoveryPhraseIntroFragment.
         */
        @JvmStatic
        fun newInstance() = RecoveryPhraseIntroFragment()
    }
}
