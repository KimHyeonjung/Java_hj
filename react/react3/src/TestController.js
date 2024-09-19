import {useState} from 'react';

function TestController(){
	let [member, setMember] = useState();
	function submit(){		

		fetch("/spring/react/signup", {
			method : 'post',
			body : JSON.stringify(member),
			headers : {
				'Content-Type' : 'application/json'
			}
		})
		.then(res => res.json())	
		.then(data => console.log(data))
		.catch(e=>console.error(e))		
	}

	function change(e){
		setMember({
			...member, 
			[e.target.name] : e.target.value
		})
	}
	return(
		<div>
			<form onSubmit={submit}>
				<div>
					<label>아이디</label>
					<input type="text" name="me_id" onChange={change}/>
				</div>
				<div>
					<label>비번</label>
					<input type="password" name="me_pw" onChange={change}/>
				</div>
				<div>
					<label>비번확인</label>
					<input type="password" name="me_pw2" />
				</div>
				<div>
					<label>이메일</label>
					<input type="email" name="me_email" onChange={change}/>
				</div>
				<div>
					<button type="submit">회원가입</button>
				</div>
			</form>
		</div>
	)
}

export default TestController;