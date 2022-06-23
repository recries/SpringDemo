package controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.MemDAO;
import dto.MemDTO;
import oracle.jdbc.proxy.annotation.Pre;

/*
 * REpresentational State Transfer(표현 상태 전이,REST)
 * API를 통해 프로그램들은 의사소통을 한다. 
 * 그리고 REST는 개발자가 API를 만들 때 준수해야 할 규칙들이다. 
      이런 규칙들을 통해 특정 URL에 접근해서 데이터들을 가져올 수 있다.
   GET(/myrest/person/list)     		자료의 조회용
   GET(/myrest/person/list/3)   		자료의 조회용
   DELETE(/myrest/person/3)     		자료의 삭제
   POST(/myrest/person/)+데이터    		      자료의 등록
   PUT(/myrest/person/update)+데이터 	       자료의 수정  

@PathVariable-URI의 경로에서 원하는 데이터를 추출하는 용도로 사용
@RequestBody - 전송된 JSON데이터를 객체로 변환해 주는 어노테이션으로 
   @ModeAndView와 유사한 역할을 하지만 JSON에서 사용된다는 점이 차이         
 */

//RestController = @Controller + @responseBody

//@RestController
@Controller
public class MemController {
	private MemDAO dao;

	public MemController() {
	}

	public void setDao(MemDAO dao) {
		this.dao = dao;
	}
	//http://localhost:8090/myapp/mem/list
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<MemDTO> listMethod(){
		return dao.list();
	}
	
	
	//http://localhost:8090/myapp/mem/list/3
	@ResponseBody
	@RequestMapping(value = "/list/{ss}", method = RequestMethod.GET)
	public MemDTO listMethod(@PathVariable("ss") int num) {
		return dao.list(num);
	}
	

	//http://localhost:8090/myapp/mem/list/3/박사랑
	@ResponseBody
	@RequestMapping(value = "/list/{num}/{name}", method = RequestMethod.GET)
	public MemDTO listMehod(@PathVariable("num")int num, @PathVariable("name") String name) {
		return dao.list(new MemDTO(num,name));
	}
	
	//JSON.stringify(): 자바스크립트 값이나 객체를 JSON문자열로 변환한다.
	/*
	 * $.ajax({
	 * 	type:'POST',
	 * 	dataType:'text',
	 * 	data:JSON.stringify({'num':20,'name':홍길동','age':30,'loc':'서울'}),
	 * 	url:'list/',
	 * 	success:function(res){}
	 * })
	 */
	

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<String> inserMethod(@RequestBody MemDTO dto){
//	public ResponseEntity<String> inserMethod(@RequestParam int num, String name){
		ResponseEntity<String> entity=null;
		System.out.println(dto.getNum() + " "+ dto.getName());
//		System.out.println("num:"+num);
//		System.out.println("name:"+name);
//		System.out.println("name:"+name);
		try {
		dao.register(dto);
		entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}catch (Exception e) {
		entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
		}
	
	
	
	
//	http://localhost:8090/myapp/mem/update
	//{"num":10,"name":"Steven"}
		@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<String> updateMethod(@RequestBody MemDTO dto){
		ResponseEntity<String> entity= null;
		try {
			dao.update(dto);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
			return entity;
	}
		
		
		
//		http://localhost:8090/myapp/mem/delete/10
		@RequestMapping(value = "/delete/{num}", method = RequestMethod.DELETE)
		public ResponseEntity<String> deleteMethod(@PathVariable("num")int num){
			ResponseEntity<String> entity= null;
			try {
				dao.delete(num);
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}catch (Exception e) {
				entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
				}
			return entity;
		}
		
}
