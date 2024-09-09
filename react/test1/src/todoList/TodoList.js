import {useState} from 'react';
import Li from './Li';
/*
input 창과 버튼, 리스트를 구성해서 버튼을 클릭하면 input창에 들어간 값이 오늘의 할일에 추가되도록 작성
1. state 변수를 추가
	- todo, setTodo
	- todoList, setTodoList => 배열
2. input창에 값을 입력하면 state변수 todo에 입력한 값으로 업데이트
3. 버튼을 클릭하면 todo의 값을 todoList에 추가
4. todoList를 이용해서 ul 태그 안에 li태그들로 구성
 - 배열의 map을 이용해서 구성(첫번째 예제 참고)
*/
function TodoList(){
	const [todo, setTodo] = useState("");
	const [todoList, setTodoList] = useState([]);
	function change(e){
		const inputText = e.target.value;
		setTodo(inputText);
	}
	function add(){
		setTodoList([...todoList, todo]);		
		setTodo("");
	}
	return (
		<div>
			<input onChange={change} value={todo}/>
			<button onClick={add}>버튼</button>
			<h4>오늘의 할일</h4>
			<ul>
				{
				todoList.map((value, index)=>{
					return <Li key={index}text={value} />
				})
				}
			</ul>
		</div>
	);
}

export default TodoList;