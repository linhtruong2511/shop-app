package application.builder;

public class ProductSearchCriteria {
	private String name;
	private Double minPrice, maxPrice;
	private String code;

	private ProductSearchCriteria(Builder builder) {
		this.name = builder.name;
		this.minPrice = builder.minPrice;
		this.maxPrice = builder.maxPrice;
		this.code = builder.code;
	}

	public String getName() {
		return name;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public String getCode() {
		return code;
	}

	public static class Builder {
		private String name;
		private Double minPrice, maxPrice;
		private String code;

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setMinPrice(Double minPrice) {
			this.minPrice = minPrice;
			return this;
		}

		public Builder setMaxPrice(Double maxPrice) {
			this.maxPrice = maxPrice;
			return this;
		}

		public Builder setCode(String code) {
			this.code = code;
			return this;
		}

		public ProductSearchCriteria build() {
			return new ProductSearchCriteria(this);
		}
	}
}
