package com.scalar.scalar.ui.creation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.scalar.scalar.R
import kotlinx.android.synthetic.main.fragment_create_ready.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CreateReadyFragment.OnCreateReadyFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CreateReadyFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CreateReadyFragment : Fragment() {

    private var listener: OnCreateReadyFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_create_ready, container, false)

        rootView.nextButton.setOnClickListener {
            listener?.onCreateReadyFragmentInteraction()
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCreateReadyFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnCreateReadyFragmentInteractionListener")
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
    interface OnCreateReadyFragmentInteractionListener {
        fun onCreateReadyFragmentInteraction()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CreateReadyFragment.
         */
        @JvmStatic
        fun newInstance() = CreateReadyFragment()

        val TAG = "CREATE_READY_FRAGMENT"
    }
}
