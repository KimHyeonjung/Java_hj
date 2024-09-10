
import { useState } from "react";

/* 
PaginationBox 컴포넌트를 생성해서 2개 만들어 활용
버튼 1/4 버튼
버튼 1/13 버튼
	- 재사용을 위해 props를 활용할 수 있는지 확인하는 예제
*/
function ButtonBox2(){
	
	return (
		<div>
			<PaginationBox maxPage={4}/>
			<PaginationBox maxPage={13}/>
		</div>
	)
}

function PaginationBox({maxPage}){
	var [page, setPage] = useState(1);

	function movePage(amount){
		let currentPage = page + amount;
		if(currentPage == 0){
			currentPage = maxPage;
		}
		if(currentPage == maxPage+1){
			currentPage = 1;
		}
		setPage(currentPage);
	}
	return (
		<div>
			<button onClick={()=>movePage(-1)}>&lt;</button>
				<span>{page}</span>
				<span> / </span>
				<span>{maxPage}</span>
			<button onClick={()=>movePage(1)}>&gt;</button>	
		</div>
	)
}

export default ButtonBox2;