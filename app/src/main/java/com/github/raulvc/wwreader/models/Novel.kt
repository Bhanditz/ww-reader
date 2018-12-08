package com.github.raulvc.wwreader.models

data class Novel(
        var name: String,
        var shortName: String?,
        var description: String?,
        var chaptersCount: Long?
)