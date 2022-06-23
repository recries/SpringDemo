package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dto.MemDTO;


//https://camle/apache.org/manual/json.html
//https://www.json.org/json-en.html
//https://github.com/google/gson
//json => apache
//gson => google


/* view
 * 1. java + spring +jsp
 * 2. ajax + jsp
 * 2. angular, react, vue
 * 
 */



@Controller
public class RestClientController {
	public RestClientController() {
		// TODO Auto-generated constructor stub
	}
	//gson
	//http://localhost:8090/myclient/list.do
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView executeSelect(ModelAndView mav) throws IOException {
		
		URL url = new URL("http://localhost:8090/myapp/mem/list");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		if(conn.getResponseCode()==HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			System.out.println(reader.readLine());
			//google
			
			List<MemDTO> aList = new ArrayList<MemDTO>();
			
//			JsonParser parser = new JsonParser();
			JsonElement element = JsonParser.parseReader(reader);
			JsonArray jsonList = element.getAsJsonArray();
			
			Gson gson = new Gson();
			for(int i=0; i<jsonList.size();i++) {
				MemDTO dto = gson.fromJson(jsonList.get(i), MemDTO.class);
				aList.add(dto);
			}
			
			reader.close();
			mav.addObject("aList",aList);
		}
		
		mav.setViewName("list");
		return mav;
		
	}
	//simple - Json
	//http://localhost:8090/myclient/list2.do
	@RequestMapping(value = "/list2.do", method = RequestMethod.GET)
	public ModelAndView executeSelect2(ModelAndView mav) throws IOException, ParseException {
		
		URL url = new URL("http://localhost:8090/myapp/mem/list");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		if(conn.getResponseCode()==HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			System.out.println(reader.readLine());
			//google
			
			List<MemDTO> aList = new ArrayList<MemDTO>();
			
		JSONParser parser = new JSONParser();
		JSONArray result = (JSONArray)parser.parse(reader);
		reader.close();
//		System.out.println(result instanceof JSONArray);
		for(int i=0; i<result.size();i++) {
			JSONObject object = (JSONObject)result.get(i);
			object.get("num");
			MemDTO dto=new MemDTO();
			dto.setNum(Integer.parseInt(object.get("num").toString()));
			dto.setName((String)object.get("name"));
//			dto.setName(object.get("name").toString());
			dto.setAge(Integer.parseInt((String)object.get("age")));
//			dto.setAge(Integer.parseInt(object.get("age").toString()));
			dto.setLoc((String)object.get("loc"));
			aList.add(dto);
		}
		mav.addObject("aList",aList);
		}
		
		mav.setViewName("list");
		return mav;
		
	}
	//http://localhost:8090/myclient/listone.do
		@RequestMapping(value = "/listone.do", method = RequestMethod.GET)
		public ModelAndView executeSelectone(ModelAndView mav) throws IOException {
			
			URL url = new URL("http://localhost:8090/myapp/mem/list/"+4);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//				System.out.println(reader.readLine());
				//google
				
				List<MemDTO> aList = new ArrayList<MemDTO>();
				
//				JsonParser parser = new JsonParser();
				JsonElement element = JsonParser.parseReader(reader);
				reader.close();
				JsonObject jsonList = element.getAsJsonObject();
				
				Gson gson = new Gson();
				
				MemDTO dto = gson.fromJson(jsonList, MemDTO.class);
				
				mav.addObject("dto", dto);
			}
			
			mav.setViewName("listone");
			return mav;
			
		}
	
}
