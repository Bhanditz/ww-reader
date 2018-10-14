package com.github.raulvc.wwreader.dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.raulvc.wwreader.R

class PreferencesFragment : Fragment() {

    companion object {

        fun newInstance(): PreferencesFragment {
            return PreferencesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_preferences, container, false)
    }
}