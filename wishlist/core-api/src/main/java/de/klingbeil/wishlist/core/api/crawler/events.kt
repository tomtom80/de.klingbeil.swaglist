package de.klingbeil.wishlist.core.api.crawler

import de.klingbeil.wishlist.core.api.wishlist.WishlistId
import de.klingbeil.wishlist.core.api.wishes.WishId

abstract class CrawleEvent(open val crawleId: CrawlerId)

data class WishRepresentationReceivedEvent(
        override val crawleId: CrawlerId,
		val wishlistId: WishlistId,
		val wishId: WishId,
        val wishName: String,
		val wishDescription: String,
        val wishLocation: String
) : CrawleEvent(crawleId)


data class WishRepresentationNotFoundEvent(
        override val crawleId: CrawlerId,
		val wishlistId: WishlistId,
		val wishId: WishId,
        val wishText: String
) : CrawleEvent(crawleId)