package auction;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member implements Serializable{
	
	private static final long serialVersionUID = 419764627536247947L;
	private String id;
	private String pw;
	private List<Item> items;
	private boolean admin;
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(id, other.id) && Objects.equals(pw, other.pw);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, pw);
	}
	
	
}
