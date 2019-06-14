package de.klingbeil.wishlist.core.api.crawler

import org.axonframework.common.IdentifierFactory
import java.io.Serializable

data class CrawlerId(val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Serializable {

    companion object {
        private const val serialVersionUID = -5267104328616955617L
    }

}