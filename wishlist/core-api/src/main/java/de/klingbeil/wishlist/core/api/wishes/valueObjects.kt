package de.klingbeil.wishlist.core.api.wishes

import org.axonframework.common.IdentifierFactory
import java.io.Serializable

data class WishId(val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Serializable {

    companion object {
        private const val serialVersionUID = -7842002574176005113L
    }

}