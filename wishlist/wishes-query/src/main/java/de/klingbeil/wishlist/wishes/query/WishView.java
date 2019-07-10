package de.klingbeil.wishlist.wishes.query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "identifier", "wishlistId" }))
public class WishView {

	@Id
	@javax.persistence.Id
	private String identifier;
	@NotNull
	@Column(name = "wishlistId")
	private String wishlistId;
	@Column(name = "wishName")
	private String wishName;
	@Length(max = 4096)
	@Column(name = "wishDescription")
	private String wishDescription;
	@Column(name = "wishLocation")
	private String wishLocation;
	@Column(name = "wishLocationUrl")
	private String wishLocationUrl;
	@Column(name = "wishImageUrl")
	private String wishImageUrl;

	public WishView() {
		// Required by Axon Framework
	}

	public WishView(String identifier, String wishlistId, String wishName, String wishDescription, String wishLocation,
			String wishLocationUrl, String wishImageUrl) {
		super();
		this.identifier = identifier;
		this.wishlistId = wishlistId;
		this.wishName = wishName;
		this.wishDescription = wishDescription;
		this.wishLocation = wishLocation;
		this.wishLocationUrl = wishLocationUrl;
		this.wishImageUrl = wishImageUrl;
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

	public String getWishLocationUrl() {
		return wishLocationUrl;
	}

	public String getWishImageUrl() {
		return wishImageUrl;
	}

}
