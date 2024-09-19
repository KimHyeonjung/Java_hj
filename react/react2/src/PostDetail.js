import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";



function PostDetail(){
	let [post, setPost] = useState({});	
	const {po_num} = useParams();		
	useEffect(() => {
		fetch('/spring/react/post/detail/'+po_num)
				.then((res) => res.json())
				.then(res=>{						
							var date = (new Date(res.po_date)).toLocaleDateString();
							res.po_date = date;							
					setPost(res);
				})
				.catch(e=>console.error(e));

}, [])
	return(
		<div>
			<div className="h2 mt-4">{post.po_title}</div>
			<div>
				<span style={{"font-size":"14px", "font-weight":"bold"}}>{post.po_me_id}</span> / <span style={{"font-size":"14px"}}>{post.po_date}</span>
			</div>			
			<hr/>
			<div>{post.po_sub}</div>
		</div>
		
	)
}

export default PostDetail;