package uni.vt.education.history.model;

public class User {

	private int id;
	private String email;
	private String password;
	private String username;
	private String rank;
	private int score;

	public User() {
		this(0, null, null, null);
	}

	public User(int id, String email, String password, String name) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	};

	
}
