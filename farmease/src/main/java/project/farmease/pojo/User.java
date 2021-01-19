package project.farmease.pojo;

import java.time.Instant;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false,length=30,unique=true)
	private String email;
	@Column(nullable = false,length=15)
	private String fname;
	@Column(nullable = false,length=15)
	private String lname;
	@Column(nullable = false,length=15)
	private String mobileno;
	@Column(nullable = false,length=20)
	private String password;
	private Instant created;
	private boolean enabled;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private Set<Booking> booking;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private Set<Address> address;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private Set<Wishlist> wishlist;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private Set<Hostuser> hostuser;
	
	@OneToOne(mappedBy = "user",cascade=CascadeType.ALL)
	private Logincreds logincreds;

	public User(Long id, String email, String fname, String lname, String mobileno, String password, Instant created,
			boolean enabled) {
		super();
		this.id = id;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.mobileno = mobileno;
		this.password = password;
		this.created = created;
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
