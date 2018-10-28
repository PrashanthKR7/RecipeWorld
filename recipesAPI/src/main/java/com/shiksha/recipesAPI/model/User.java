package com.shiksha.recipesAPI.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
@NamedQuery(name = "Person.findByEmailId", query = "SELECT 1 FROM User u where u.emailId = :emailId")
@Table(name = "user")
public class User extends DateAudit implements Serializable {

	private static final long serialVersionUID = 8195794234367392411L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Size(min = 3, max = 40, message = "First Name should have atleast 3 characters")
	@NotBlank
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = true)
	private String lastName;

	@NaturalId
	@Email
	@Column(name = "email_id", nullable = false)
	private String emailId;

	@NotBlank
	@Size(max = 15)
	private String username;

//	@Size(min = 3, message = "First Name should have atleast 3 characters")
//	@Column(name = "mobile_number", nullable = false)
//	private String mobileNumber;
//
//	@Column(name = "gender", nullable = true)
//	private char gender;
//
//	@Column(name = "thumbnail", nullable = true)
//	private String thumbnail;

	@PastOrPresent(message = "User activitiy in the future cannot be determined.")
	@Column(name = "last_active_on", nullable = true)
	private Date lastActiveOn;

	@Column(name = "is_author", nullable = true)
	private char isAuthor;

	@NotBlank
	@Size(max = 100)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {

	}

	public User(String firstName, String lastName, String emailId, /*
																	 * String mobileNumber, char gender, String
																	 * thumbnail,
																	 */
			Date lastActiveOn) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
//		this.mobileNumber = mobileNumber;
//		this.gender = gender;
//		this.thumbnail = thumbnail;
		this.lastActiveOn = lastActiveOn;
	}

	public User(String firstName,String lastName, String username, String emailId, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.emailId = emailId;
		this.password = password;
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

	@Override
	public String toString() {
		return "User [userId=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", lastActiveOn=" + lastActiveOn + ", isAuthor=" + isAuthor + ", password=" + password + ", roles="
				+ roles + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isAuthor != other.isAuthor)
			return false;
		if (lastActiveOn == null) {
			if (other.lastActiveOn != null)
				return false;
		} else if (!lastActiveOn.equals(other.lastActiveOn))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setUserId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
//	public String getMobileNumber() {
//		return mobileNumber;
//	}
//
//	public void setMobileNumber(String mobileNumber) {
//		this.mobileNumber = mobileNumber;
//	}
//
//	public char getGender() {
//		return gender;
//	}
//
//	public void setGender(char gender) {
//		this.gender = gender;
//	}
//
//	public String getThumbnail() {
//		return thumbnail;
//	}
//
//	public void setThumbnail(String thumbnail) {
//		this.thumbnail = thumbnail;
//	}

	public Date getLastActiveOn() {
		return lastActiveOn;
	}

	public void setLastActiveOn(Date lastActiveOn) {
		this.lastActiveOn = lastActiveOn;
	}


}
