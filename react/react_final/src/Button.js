
function Button1(props){	
	return (
		<button className={props.classNames} onClick={props.click}>{props.text}</button>
	)
}

const Button2 = ({text, styles, click})=>{		
	return(
		<button style={styles} onClick={click}>{text}</button>
	)
}

export {Button1, Button2};