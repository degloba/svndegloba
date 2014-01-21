package com.insacosa.Inmobles.webui;

/**
 * Helper DTO to created by a controller (and not by finder like other DTOs).
 * 
 * @author Rafał Jamróz
 */
public class SolicitudItemDto {
    private Long productId;
    private String productName;
    private Integer count;

    public SolicitudItemDto(Long productId, String productName, Integer count) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.count = count;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
