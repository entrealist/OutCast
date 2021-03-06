package com.caldeirasoft.outcast.domain.models

import com.caldeirasoft.outcast.domain.interfaces.StoreCollection
import com.caldeirasoft.outcast.domain.interfaces.StoreDataWithCollections
import com.caldeirasoft.outcast.domain.interfaces.StoreDataWithLookup
import com.caldeirasoft.outcast.domain.interfaces.StoreItem
import kotlinx.serialization.Serializable

@Serializable
class StoreMultiRoom(
    var id: Long,
    var label: String,
    val url: String = "",
    var description: String? = null,
    override var artwork: Artwork? = null,
    override val storeList: List<StoreCollection> = arrayListOf(),
    override var lookup: Map<Long, StoreItem> = mutableMapOf()
) : StoreItem, StoreDataWithLookup, StoreDataWithCollections {
    override fun getArtworkUrl(): String =
        artworkUrl(400, 196, crop = "fa")
}