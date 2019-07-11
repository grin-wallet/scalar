package com.scalar.scalar.ui.creation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scalar.scalar.R
import kotlinx.android.synthetic.main.fragment_phrase.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PhraseFragment.OnPhraseFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PhraseFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PhraseFragment : Fragment() {
    private var listener: OnPhraseFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_phrase, container, false)

        rootView.nextButton.setOnClickListener {
            listener?.onPhraseButtonClicked()
        }

        rootView.phraseView.loadMnemonic(getPhrase())

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPhraseFragmentInteractionListener) {
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
    interface OnPhraseFragmentInteractionListener {
        fun onPhraseButtonClicked()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment.
         *
         * @return A new instance of fragment PhraseFragment.
         */
        @JvmStatic
        fun newInstance() = PhraseFragment()

        val TAG = "PHRASE_FRAGMENT"
    }

    private fun getPhrase(): List<String> {
        return listOf("burger", "king", "pizza", "coin", "dollar", "log", "google", "facebook",
                "amazon", "learn", "eat", "sleep", "brush", "laugh", "pray")
    }

}
