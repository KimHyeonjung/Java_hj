
import { useState } from "react";

function PostInsert({list, setList}){
	let [title, setTitle] = useState("");
	let [writer, setWriter] = useState("");
	let [content, setContent] = useState("");
	let inputTitle = (e)=> setTitle(e.target.value);
	let inputWriter = (e)=> setWriter(e.target.value);
	let inputContent = (e)=> setContent(e.target.value);
	let click = ()=>{
		//입력한 제목, 작성자, 내용을 이용해서 객체로 만들고 만들어진 객체를 콘솔에 출력
		// let post = {
		// 	title : title,
		// 	writer : writer,
		// 	content : content
		// }
		//변수 이름이 같은 경우 
		var view = 0;
		let post = {
			title, writer, content, view
		}
		console.log(post);
		
		setList([post, ...list]);

		//제목, 작성자, 내용 입력창에 입력된 값들을 지움
		setTitle("");
		setWriter("");
		setContent("");
	}
	return(
		<div>
				<label>제목</label>
				<input type="text" id="title" name="title" value={title} placeholder="제목을 입력하세요" onChange={inputTitle}/>
				<label>작성자</label>
				<input id="writer" name="writer" value={writer} placeholder="작성자를 입력하세요" onChange={inputWriter}/>
				<label>내용</label>
				<textarea id="content" name="content" value={content} placeholder="내용을 입력하세요" onChange={inputContent}></textarea>
				<button className="btn" onClick={click}>게시글 등록</button>
		</div>	
	)
}

export default PostInsert;