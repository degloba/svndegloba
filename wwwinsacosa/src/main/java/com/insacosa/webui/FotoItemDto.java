package com.insacosa.webui;

import com.google.appengine.api.datastore.Key;

/**
 * Helper DTO to created by a controller (and not by finder like other DTOs).
 * 
 * @author Rafał Jamróz
 */
public class FotoItemDto {
    private Long productId;
    private String productName;
    private Integer count;

       
    
    public FotoItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FotoItemDto(Long productId, String productName, Integer count) {
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

	public String getCiutatKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setKey(Key id) {
		// TODO Auto-generated method stub
		
	}

	public void setCode(String code) {
		// TODO Auto-generated method stub
		
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getProperty(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescripcio() {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] getImatge() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setImatge(byte[] imatge) {
		// TODO Auto-generated method stub
		
	}

	public void setDescripcio(String descripcio) {
		// TODO Auto-generated method stub
		
	}
}
