import {BrowserRouter, Link, Route, Routes} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';


function NavEx(){
	return(
		// <nav>
		// 	<ul>
		// 		<li>
		// 			<Link to={"/"}>홈</Link>
		// 		</li>
		// 		<li>
		// 			<Link to={"/post/list/0"}>커뮤니티</Link>
		// 		</li>
		// 	</ul>
			
		// </nav>
		<Navbar expand="lg" className="bg-body-tertiary">
		<Container>			
			<Navbar.Toggle aria-controls="basic-navbar-nav" />
			<Navbar.Collapse id="basic-navbar-nav">
				<Nav className="me-auto">					
						<Link className="nav-link" to={"/"}>홈</Link>	
						<Link className="nav-link" to={"/post/list/0"}>커뮤니티</Link>	
				</Nav>
			</Navbar.Collapse>
		</Container>
	</Navbar>
	)
}

export default NavEx;