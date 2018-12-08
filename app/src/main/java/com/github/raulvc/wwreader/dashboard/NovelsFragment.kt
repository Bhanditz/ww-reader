package com.github.raulvc.wwreader.dashboard

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import com.github.raulvc.wwreader.R
import com.github.raulvc.wwreader.models.Novel
import com.github.raulvc.wwreader.services.WWService
import kotlinx.android.synthetic.main.fragment_novels.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class NovelsFragment : Fragment()  {

    companion object {

        fun newInstance(): NovelsFragment {
            return NovelsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // first run, updates novel list
        updateNovels()
        return inflater.inflate(R.layout.fragment_novels, container, false)
    }

    private fun updateNovels() {
        GlobalScope.launch(Dispatchers.Main) {
            redrawNovels(WWService.newInstance().getNovelList())
        }
    }

    private fun redrawNovels(updatedNovelList: List<Novel>) {
        for (novel in updatedNovelList) {
            // placing new widget
            val context = novelsContainer.context
            novelsContainer.addView(newNovelLayout(novel, context))
        }
    }

    private fun newNovelLayout(novel: Novel, context: Context) : LinearLayout {
        val novelLayout = LinearLayout(context)
        novelLayout.orientation = LinearLayout.HORIZONTAL

        // for accessing a novel
        val viewButton = Button(context).apply {
            text = novel.name
            layoutParams = LinearLayout
                    .LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT, 0.09f
                    )
        }

        // subscribe shortcut
        val subscribed = isSubscribed(novel)
        val subscribeButton = ImageButton(context).apply {
            setImageResource(
                    if (subscribed)
                        R.drawable.ic_notifications_black_24dp // bell icon
                    else
                        R.drawable.ic_mtrl_chip_checked_circle
            )
            setBackgroundColor(
                    ContextCompat.getColor(
                            context,
                            if (subscribed)
                                android.R.color.transparent
                            else
                                android.R.color.holo_blue_bright
                    )
            )
            layoutParams = LinearLayout
                    .LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT, 0.91f
                    )
        }

        novelLayout.addView(viewButton)
        novelLayout.addView(subscribeButton)

        return novelLayout
    }

    private fun isSubscribed(novel: Novel): Boolean {
        return Random.nextBoolean()
    }
}