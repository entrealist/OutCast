package com.caldeirasoft.outcast.domain.dto

import com.caldeirasoft.outcast.domain.models.Genre
import kotlinx.serialization.Serializable


/**
 * Created by Edmond on 12/02/2018.
 */
@Serializable
class GenreDto (
    var genreId: Int,
    var name: String = "",
    var url: String = "") {

    fun toGenre() =
        Genre(
            id = genreId,
            name = name,
            url = url
        )

    companion object {
        fun toGenre(dto: GenreDto) =
            Genre(
                id = dto.genreId,
                name = dto.name,
                url = dto.url
            )
    }
}