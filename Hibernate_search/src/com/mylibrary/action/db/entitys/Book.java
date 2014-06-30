package com.mylibrary.action.db.entitys;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
public class Book {

	@Id
	@GeneratedValue
	@DocumentId
	private Integer id;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	private String title;

	@Field(index = Index.UN_TOKENIZED, store = Store.NO)
	private String isbn;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	private String publisher;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	@Column(name = "description", length = 1500)
	private String desc;

	@Field(index = Index.UN_TOKENIZED, store = Store.NO)
//	@NumericField(precisionStep = 6)
	// precisionStep : default value = 4
	private float price;

	@Field(name = "dateOfPublication", index = Index.UN_TOKENIZED)
	@DateBridge(resolution = Resolution.YEAR)
	private Date dateOfPublication;

	@ManyToMany(cascade = { javax.persistence.CascadeType.ALL }, fetch = FetchType.EAGER)
	@IndexedEmbedded
	private Set<Author> authors = new HashSet<Author>();

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Author> getAuthors() {
		return this.authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getDateOfPublication() {
		return dateOfPublication;
	}

	public void setDateOfPublication(Date dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", isbn=" + isbn
				+ ", publisher=" + publisher + ", desc=" + desc + ", price="
				+ price + ", dateOfPublication=" + dateOfPublication
				+ ", authors=" + authors + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Integer id, String title, String isbn, String publisher,
			String desc, float price, Date dateOfPublication) {
		super();
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.publisher = publisher;
		this.desc = desc;
		this.price = price;
		this.dateOfPublication = dateOfPublication;
	}
	
	

}