package day18.homework.v2;

import java.util.List;

import day18.homework.v1.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
	private String id;
	private String name;
	private List<Schedule> scList;
	
	
	
}
