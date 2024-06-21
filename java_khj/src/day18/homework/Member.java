package day18.homework;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
	private String id;
	private String name;
	private List<Schedule> scList;
	
	
}
