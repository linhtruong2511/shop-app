package application.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductCreationRequest {
    @NotNull(message = "product name is not null")
    private String name;
    @NotNull(message = "product code is not null")
    private String code;
    @NotNull(message = "product price is not null")
    private Double price;
    @Min(value = 0, message = "product quantity is not less than 0")
    @NotNull(message = "product stock quantity is not null")
    private Integer stockQuantity;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
