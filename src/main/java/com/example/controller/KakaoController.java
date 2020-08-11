package com.example.controller;

import com.example.domain.user.KakaoUserInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/kakao")
public class KakaoController {

    @GetMapping(value="/oauth")
    public String kakaoConnect() {

        StringBuffer url = new StringBuffer();
        url.append("https://kauth.kakao.com/oauth/authorize?");
        url.append("client_id=" + "ab16ebf40f5d16e2f8d41a3863e87fb1");
        url.append("&redirect_uri=http://localhost:8080/kakao/callback");
        url.append("&response_type=code");

        return "redirect:" + url.toString();
    }

    @GetMapping(value="/logout")
    public String kakaoLogout(){
        StringBuffer url = new StringBuffer();
        url.append("https://kauth.kakao.com/oauth/logout?");
        url.append("client_id=" + "ab16ebf40f5d16e2f8d41a3863e87fb1");
        url.append("&logout_redirect_uri=http://localhost:8080/kakao/logout_callback");

        return "redirect:" + url.toString();
    }

    @RequestMapping(value="/logout_callback",produces="application/json",method= {RequestMethod.GET, RequestMethod.POST})
    public String kakaoLogout_callback() {
        return "home";
    }

    @RequestMapping(value="/callback",produces="application/json",method= {RequestMethod.GET, RequestMethod.POST})
    public String kakaoLogin(@RequestParam("code")String code, RedirectAttributes ra, HttpSession session, HttpServletResponse response, Model model)throws IOException {

        System.out.println("kakao code:"+code);
        JsonNode access_token = getKakaoAccessToken(code);

        JsonNode userInfo = KakaoUserInfo.getKakaoUserInfo(access_token.get("access_token"));

        // Get id
        String member_id = userInfo.get("id").asText();

        String member_name = null;

        // 유저정보 카카오에서 가져오기 Get properties
        JsonNode properties = userInfo.path("properties");
        JsonNode kakao_account = userInfo.path("kakao_account");
        member_name = properties.path("nickname").asText(); //이름 정보 가져오는 것
        String thumbnail_image = properties.path("thumbnail_image").asText();   // 썸네일 정보 가져오기
        // email = kakao_account.path("email").asText();
//        if(member_name!=null) {
//            session.setAttribute("isLogOn",true);
//            session.setAttribute("member_id",member_name);        //여기 if문 안에 내용은 다 삭제해도 됩니다. 제 프로젝트에만 필요한 코드임.
//        }
        System.out.println("id : " + member_id);    //여기에서 값이 잘 나오는 것 확인 가능함.
        System.out.println("name : " + member_name);
        // System.out.println("email : " + email);
        model.addAttribute("username",member_name);
        model.addAttribute("thumbnailImage",thumbnail_image);
        return "home";
    }

    public JsonNode getKakaoAccessToken(String code) {
        final String RequestUrl = "https://kauth.kakao.com/oauth/token"; // Host
        final List<NameValuePair> postParams = new ArrayList<NameValuePair>();

        postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
        postParams.add(new BasicNameValuePair("client_id", "ab16ebf40f5d16e2f8d41a3863e87fb1")); // REST API KEY
        postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/kakao/callback")); // 리다이렉트 URI
        postParams.add(new BasicNameValuePair("code", code)); // 로그인 과정중 얻은 code 값

        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(RequestUrl);

        JsonNode returnNode = null;

        try {
            post.setEntity(new UrlEncodedFormEntity(postParams));

            final HttpResponse response = client.execute(post);
            final int responseCode = response.getStatusLine().getStatusCode();

            System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
            System.out.println("Post parameters : " + postParams);
            System.out.println("Response Code : " + responseCode);

            // JSON 형태 반환값 처리
            ObjectMapper mapper = new ObjectMapper();

            returnNode = mapper.readTree(response.getEntity().getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnNode;
    }
}
