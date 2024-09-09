import PropTypes from 'prop-types';

function Li({text}){
	return(
		<li>{text}</li>
		
	);
}
Li.propTypes = {
	
	text : PropTypes.string.isRequired
	
}
export default Li;