package com.example.spacex.model

import androidx.annotation.Keep

@Keep
data class listaRespuesta(
    var type : String = "",
    var media : String = "",
    var urlWeb : String = "",
    var urlImg : String = "",
    var title : String = "",
    var overview : String = "",
    var name : String = "",
    var date_utc: String = ""
)


@Keep
data class responseData(
    var success :Boolean? = false,
    val type : String? = "",
    val media : String? = "",
    var links : links? = links(),
    var name : String? = "",
    var date_utc : String? = ""

    )

@Keep
data class links(
    var patch : patch = patch(),
    var reddit : reddit = reddit()
)


@Keep
data class reddit(
    var campaign : String? = null
)

@Keep
data class patch(
    var small : String? = null,
    var large : String? = null
)
