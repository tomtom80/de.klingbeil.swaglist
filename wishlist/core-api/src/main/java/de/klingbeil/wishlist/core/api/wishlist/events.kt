package de.klingbeil.wishlist.core.api.wishlist

abstract class WishlistEvent(open val wishlistId: WishlistId)

data class WishlistCreatedEvent(
        override val wishlistId: WishlistId,
        val wishlistName: String,
        val wishlistType: WishlistType
) : WishlistEvent(wishlistId)

data class WishlistUpdatedEvent(
        override val wishlistId: WishlistId,
        val wishlistName: String,
        val wishlistType: WishlistType
) : WishlistEvent(wishlistId)

data class WishlistDeletedEvent(
        override val wishlistId: WishlistId
) : WishlistEvent(wishlistId)


data class WishlistOpenedEvent(
        override val wishlistId: WishlistId
) : WishlistEvent(wishlistId)

