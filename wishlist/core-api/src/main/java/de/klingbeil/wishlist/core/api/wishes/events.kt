package de.klingbeil.wishlist.core.api.wishes

import de.klingbeil.wishlist.core.api.wishlist.WishlistId

abstract class WishEvent(open val wishId: WishId)

data class WishUrlAddedEvent(
	override val wishId: WishId,
	val wishlistId: WishlistId,
	val wishUrl: String
) : WishEvent(wishId)

data class WishNameAddedEvent(
	override val wishId: WishId,
	val wishlistId: WishlistId,
	val wishName: String
) : WishEvent(wishId)


data class WishUpdatedEvent(
	override val wishId: WishId,
	val wishlistId: WishlistId,
	val wishName: String,
	val wishDescrition: String,
	val wishLocation: String
) : WishEvent(wishId)


data class WishPriorityIncreased(
	override val wishId: WishId,
	val wishlistId: WishlistId
) : WishEvent(wishId)

data class WishPriorityDecreased(
	override val wishId: WishId,
	val wishlistId: WishlistId
) : WishEvent(wishId)


