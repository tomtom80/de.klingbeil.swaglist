package de.klingbeil.wishlist.core.api.wishlist


import org.axonframework.common.IdentifierFactory
import java.io.Serializable

enum class WishlistType {
    PRIVAT, PUBLIC
}

data class WishlistId(val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Serializable {

    companion object {
        private const val serialVersionUID = -2521069615900157076L
    }

}
