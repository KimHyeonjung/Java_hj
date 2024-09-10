import { useState } from "react";
/* 
input태그 버튼 input태그 구성으로 화면을 생성
input태그 1에 분을 입력하면 input태그2는 초로 변환해서 출력
*/

function Input2(){
	let [flag, setFlag] = useState(true);	
	let [time, setTime] = useState(0);
	let convert = e=> setTime(e.target.value );	
	let click = ()=>{
		setFlag(!flag);	
		setTime(0);
	}	
	return(
		<div>
			<label>분</label>
			<input type="text" disabled={!flag}  value={flag? time : Math.floor(time/60)} onChange={convert} />
			<button onClick={click}>변환</button><br></br>
			<input type="text" disabled={flag}  value={flag? time * 60 : time} onChange={convert} />
			<label>초</label>
		</div>
	)
}

export default Input2;