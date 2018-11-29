package com.shiksha.recipesAPI.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import lombok.Data;

@Data
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

	@ManyToMany()
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String firstName, String lastName, String emailId, /*
																	 * String mobileNumber, char gender, String
																	 * thumbnail,
																	 */
			Date lastActiveOn) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
//		this.mobileNumber = mobileNumber;
//		this.gender = gender;
//		this.thumbnail = thumbnail;
		this.lastActiveOn = lastActiveOn;
	}

	public User(String firstName, String lastName, String username, String emailId, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.emailId = emailId;
		this.password = password;
	}

}
