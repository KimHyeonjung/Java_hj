import "./ButtonBox3.css";
import { useState } from "react";

/* 
Button 컴포넌트를 만들어서 다양한 버튼을 생성할 수 있게 작성하세요.
다양한 버튼
- 버튼 문구
- 버튼 모양
- type
*/
function ButtonBox3(){
	let style = {
		color : "yellow",
		"font-size" : "24px"
	}
	
	return (
		<form>
			<div>
				<input type="text" name="test" />
			</div>
				<Button type={"submit"} text={"전송"} className={"tomato"}/>
				<Button type={"button"} text={"버튼"} className={"tomato br-30"}  style={style}/>
				<Button type={"reset"} text={"리셋"} className={"tomato"}/>			
		</form>
	)
}

function Button({type, text, className, style}){
	return(
		
			<button type={type} className={className} style={style}>{text}</button>
		
	)
}


export default ButtonBox3;