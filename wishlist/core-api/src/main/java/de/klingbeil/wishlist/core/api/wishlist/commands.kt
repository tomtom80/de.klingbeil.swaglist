package de.klingbeil.wishlist.core.api.wishlist

import de.klingbeil.wishlist.core.api.users.UserId
import org.axonframework.modelling.command.TargetAggregateIdentifier

abstract class WishlistCommand(@TargetAggregateIdentifier open val wishlistId: WishlistId)

data class CreateWishlistCommand(
	override val wishlistId: WishlistId,
	val wisherId: UserId,
	val wishlistName: String,
	val wishlistType: WishlistType
) : WishlistCommand(wishlistId)

data class UpdateWishlistCommand(
	override val wishlistId: WishlistId,
	val wishlistName: String,
	val wishlistType: WishlistType
) : WishlistCommand(wishlistId)

data class DeleteWishlistCommand(
	override val wishlistId: WishlistId
) : WishlistCommand(wishlistId)

data class OpenWishlistCommand(
	override val wishlistId: WishlistId
) : WishlistCommand(wishlistId)