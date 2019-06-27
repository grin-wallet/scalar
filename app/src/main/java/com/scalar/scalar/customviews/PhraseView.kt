package com.scalar.scalar.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.scalar.scalar.R
import kotlinx.android.synthetic.main.phrase_custom_view.view.*

class PhraseView(context: Context?, attrs: AttributeSet?) : ScrollView(context, attrs) {

    init {
        inflate(getContext(), R.layout.phrase_custom_view, this)
    }

    fun loadMnemonic (recoveryPhrase : List<String>) {
        val LAYOUT_MARGINS = 16

        for (i in recoveryPhrase.indices) {
            @SuppressLint("InflateParams")
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_phrase_view, null)

            val numberTextView = itemView!!.findViewById<TextView>(R.id.numberItem)
            val wordTextView = itemView.findViewById<TextView>(R.id.wordItem)

            numberTextView.text = (i + 1).toString()
            wordTextView.text = recoveryPhrase[i]

            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(LAYOUT_MARGINS, LAYOUT_MARGINS, LAYOUT_MARGINS, LAYOUT_MARGINS)

            mnemonicGridView.addView(itemView, i, layoutParams)
        }
    }
}