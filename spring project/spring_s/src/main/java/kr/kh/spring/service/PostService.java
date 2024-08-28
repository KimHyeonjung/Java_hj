package kr.kh.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.spring.dao.PostDAO;

@Service
public class PostService {
	
	@Autowired
	PostDAO postDao;
}
