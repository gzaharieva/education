package uni.vt.education.history.model;

public class User {

	private String email;
	private String password;
	private String name;
	private String rank;
	private int score;

	public User() {
		this(null, null, null, null, 0);
	}

	public User(String email, String password, String name, String rank,
			int score) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.rank = rank;
		this.score = score;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	};

}
