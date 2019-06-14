package de.klingbeil.wishlist.core.api.crawler

import de.klingbeil.wishlist.core.api.wishlist.WishlistId
import de.klingbeil.wishlist.core.api.wishes.WishId
import org.axonframework.modelling.command.TargetAggregateIdentifier

abstract class CrawleCommand(@TargetAggregateIdentifier open val crawleId: CrawlerId)

data class CrawleUrlCommand(
	override val crawleId: CrawlerId,
	val wishlistId: WishlistId,
	val wishId: WishId,
	val url: String
) : CrawleCommand(crawleId)