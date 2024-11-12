package kr.kh.spring.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {

    private static final String serviceKey = "nK9kzaLjuDXBirERtqoQ0ItL4YS3TmebT8fcQFe%2BgDlpcoJLFhwpJeCyU%2BmGv%2FvyEE5LzRudVDDv15j%2BzGdk4g%3D%3D";

    @ResponseBody
    @RequestMapping(value="air.do", produces = "application/json; charset=utf-8")
    public String airPollution(String location) throws IOException {
        
    	String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
        
    	String parameter = "?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey
			    			+ "&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")
			    			+ "&" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode(location, "UTF-8"); 
        url = url + parameter;
        // 아래 내용은 수정 불필요
        URL requestUrl = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String responseText = "";
        String line;
        while((line = br.readLine()) != null) {
            responseText += line;
        }
        br.close();
        urlConnection.disconnect();
        
        // System.out.println(responseText);
        return responseText;
    }
    
    @GetMapping("/searchair")
    public String air() {
    	return "/data/index";
    }
}

