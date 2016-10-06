package com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify;


import com.degloba.organisation.domain.Address;
import com.degloba.organisation.domain.Email;
import com.degloba.organisation.domain.PersonName;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;
import java.util.*;

@Entity
public class Owner implements Serializable {
  
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	String id;
	
	//////////@Embedded
    PersonName name;
    
	String firstName;
    String lastName;
    
    String address;

    /*@ElementCollection
    @CollectionTable(name = "person_emails", joinColumns = @JoinColumn(name = "person_id"))
 */   private Set<Email> emails = new HashSet<Email>();

    
   /* @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "province", column = @Column(name = "home_province")),
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "detail", column = @Column(name = "home_detail"))
    })*/
    Address homeAddress;

    
   /* @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "province", column = @Column(name = "mail_province")),
            @AttributeOverride(name = "city", column = @Column(name = "mail_city")),
            @AttributeOverride(name = "detail", column = @Column(name = "mail_detail"))
    })*/
    Address mailAddress;
    

    public Owner(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }
    
  
    List<Key<Product>> products = new ArrayList<Key<Product>>();
	
    

    public Set<Email> getEmails() {
        return Collections.unmodifiableSet(emails);
    }

    public void setEmails(Set<Email> emails) {
        if (emails == null) {
            return;
        }
        this.emails = new HashSet<Email>(emails);
    }

    public void addEmail(Email email) {
        emails.add(email);
    }

    public void removeEmail(Email email) {
        emails.remove(email);
    }

   
    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(Address mailAddress) {
        this.mailAddress = mailAddress;
    }

    /*@Override
    public String[] businessKeys() {
        return new String[]{"name", "idNumber"};
    }*/

    @Override
    public String toString() {
        return name.toString();
    }

	public Owner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonName getName() {
		return name;
	}

	public void setName(PersonName name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Key<Product>> getProducts() {
		return products;
	}

	public void setProducts(List<Key<Product>> products) {
		this.products = products;
	}

	

	
	
	
}