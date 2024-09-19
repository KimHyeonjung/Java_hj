
import { Container, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavEx from "./Nav";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Main from "./Main";
import PostList from "./PostList";
import PostDetail from "./PostDetail";


function App() {
   
    return (
        <BrowserRouter>    
          <NavEx />
            <Container>
                <Routes>           
                    <Route path={"/"} exact element={<Main/>} />
                    <Route path={"/post/list/:co_num"} element={<PostList/>} />
                    <Route path={"/post/detail/:po_num"} element={<PostDetail/>} />                            
                </Routes>               
            </Container>
        </BrowserRouter>
    );
}

export default App;