package auction;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member implements Serializable{
	
	private static final long serialVersionUID = 419764627536247947L;
	private String id;
	private String pw;
	private Item item;
	private boolean admin;
	
	
}
