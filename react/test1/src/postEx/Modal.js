
function Modal({funcClose, list, index}){
	return(
		<div id="modal">
					<div id="modal-content">
						<div>
							<div>번호</div>
							<div>{list.length - index}</div>
						</div>
						<div>
							<div>제목</div>
							<div>{list[index].title}</div>
						</div>
						<div>
							<div>작성자</div>
							<div>{list[index].writer}</div>
						</div>						
						<div>
							<div>내용</div>
							<div>{list[index].content}</div>
						</div>		
						<button className={"btn"} onClick={funcClose}>닫기</button>				
					</div>
				</div>
	)
}

export default Modal;