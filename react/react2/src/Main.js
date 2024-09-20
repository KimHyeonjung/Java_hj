import {useEffect, useState} from "react";
import { Link } from "react-router-dom";



function Main() {
    const [list, setList] = useState([]);

    useEffect(() => {
        
        //커뮤니티 리스트
        fetch('/spring/react/community/list')
            .then((res) => res.json())
            .then(res=>{
              setList(res);
              console.log(res)
            })
    }, []);
    return (
        <div>  
            <h1>커뮤니티 목록</h1>
            { 
              list.map((item,index)=>{
                return (
                  <div key={index} className="d-flex justify-content-between">
                    <Link className="btn btn-outline-success" to={"/post/list/"+item.co_num}>{item.co_name}</Link>
                  </div>
                )
              })
            }
        </div>
    );
}

export default Main;