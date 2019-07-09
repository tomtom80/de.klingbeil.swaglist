package de.klingbeil.wishlist.wishes.query;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.Id;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "identifier", "wishlistId" }))
public class WishView {

	@Id
	@javax.persistence.Id
	private String identifier;
	private String wishlistId;
	private String wishName;
	private String wishDescription;
	private String wishLocation;

	public WishView() {
		// Required by Axon Framework
	}

	public WishView(String identifier, String wishlistId, String wishName, String wishDescription,
			String wishLocation) {
		super();
		this.identifier = identifier;
		this.wishlistId = wishlistId;
		this.wishName = wishName;
		this.wishDescription = wishDescription;
		this.wishLocation = wishLocation;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getWishlistId() {
		return wishlistId;
	}

	public String getWishName() {
		return wishName;
	}

	public String getWishDescription() {
		return wishDescription;
	}

	public String getWishLocation() {
		return wishLocation;
	}

}
