package de.klingbeil.wishlist.core.api.wishes

import org.axonframework.modelling.command.TargetAggregateIdentifier
import de.klingbeil.wishlist.core.api.wishlist.WishlistId

abstract class WishCommand(@TargetAggregateIdentifier open val wishId: WishId)

data class AddWishToWishlistCommand(
	override val wishId: WishId,
	val wishlistId: WishlistId,
	val wishText: String
) : WishCommand(wishId)

data class RemoveWishFromWishlistCommand(
	override val wishId: WishId,
	val wishlistId: WishlistId
) : WishCommand(wishId)

data class WishUpdateCommand(
	override val wishId: WishId,
	val wishlistId: WishlistId,
	val wishName: String,
	val wishDescription: String,
	val wishLocation: String 
): WishCommand(wishId)


data class IncreaseWishPriorityCommand (
	override val wishId: WishId,
	val wishlistId: WishlistId
): WishCommand(wishId)


data class DecreaseWishPriorityCommand (
	override val wishId: WishId,
	val wishlistId: WishlistId
): WishCommand(wishId)