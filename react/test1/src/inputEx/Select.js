import { useState } from "react";

/* 
select 태그를 이용해서 과일을 선택할 수 있는 창을 만들고, 
과일을 선택하면 h1태그에 선택한 과일이 출력되도록 작성
 - 선택안함, 사과, 바나나, 오렌지
*/
function Select(){
	const list = ['선택안함', '사과', '바나나', '오렌지'];
	let [fruit, setFruit] = useState("");
	let select = (e)=>setFruit(e.target.value);
	
	return (
		<div>
			<select onChange={select}>
			{
				list.map((v,i)=>{
					return (
						<option key={i} value={v}>{v}</option>
					)
				})
			}
			</select>
			<h1>{fruit == "선택안함" ? "" : fruit}</h1>
		</div>
	)
}

export default Select;