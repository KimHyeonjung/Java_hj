package servlet1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	
	public LoginDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	private String id;
	private String pw;
	private String email;
}
