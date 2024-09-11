import { useState } from "react";
import Modal from "./Modal";

function PostList({list, setList}){
	
	let [display, setDisplay] = useState(false);
	let [p, setP] = useState({});

	function modalClick(index){
		let tmp = [...list];
		tmp[index].view++;
		setList(tmp);	
		setDisplay(true);
		setP(index);		
	}
	function delPost(index){
		setList(list.filter((item, i)=>index != i));
	}
	return(
		<div id="table-box">
				<h1>게시글 목록</h1>
				<table border="1">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>기능</th>
						</tr>
					</thead>
					<tbody>
						{
							list.map((p,index)=>{
								return(
									<tr key={index}>
										<td>{list.length - index}</td>
										<td><a onClick={()=>modalClick(index)} style={{cursor:"pointer"}}>{p.title}</a></td>
										<td>{p.writer}</td>
										<td>{p.view}</td>				
										<td>
											<button onClick={()=>delPost(index)}>삭제</button>	
										</td>				
									</tr>
								)
							})
						}
					</tbody>					
				</table>
				{
					display ?  <Modal funcClose={()=>setDisplay(false)} post={p} index={index}/> : ""
				}
			</div>
	)
}
export default PostList;