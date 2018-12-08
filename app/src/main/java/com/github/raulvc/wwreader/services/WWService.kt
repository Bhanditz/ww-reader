package com.github.raulvc.wwreader.services

import com.github.raulvc.wwreader.models.Novel

class WWService {

    companion object {

        fun newInstance(): WWService {
            return WWService()
        }
    }

    fun getNovelList() : List<Novel> {
        val novels = ArrayList<Novel>()
        novels.add(Novel("NovelExample1", "NE1", "Some nice novel", 30))
        novels.add(Novel("NovelExample2", "NE2", "Some nice novel", 30))
        novels.add(Novel("NovelExample3", "NE3", "Some nice novel", 30))
        novels.add(Novel("NovelExample4", "NE4", "Some nice novel", 30))
        return novels
    }
}