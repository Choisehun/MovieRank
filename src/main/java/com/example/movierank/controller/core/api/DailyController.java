package com.example.movierank.controller.core.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class DailyController {


    @GetMapping("/main/daily")
    public String getAPI() {
        System.out.println("데일리");
        //오늘날짜
        LocalDate today = LocalDate.now();
        //오늘날짜에서 -1일
        LocalDate yesterday = today.minusDays(1);
        //날짜 data xxxx-xx-xx 로들어오기때문에 - 삭제해줌
        String numericStr = yesterday.toString().replaceAll("-", "");

        HashMap<String, Object> result = new HashMap<String, Object>();
        String jsonInString = "";

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);
            String url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url + "?" + "key=f5eef3421c602c6cb7ea224104795888&targetDt=" + numericStr).build();
            System.out.println("주소: " + uri);
            //이 한줄의 코드로 API를 호출해 MAP타입으로 전달 받는다.
            ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);

            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

            ///
            LinkedHashMap lm = (LinkedHashMap) resultMap.getBody().get("boxOfficeResult");

            ArrayList<Map> dboxoffList = (ArrayList<Map>) lm.get("dailyBoxOfficeList");

            LinkedHashMap mnList = new LinkedHashMap<>();

            for (Map obj : dboxoffList) {
                LinkedHashMap movieInfo = new LinkedHashMap();
                movieInfo.put("name", obj.get("movieNm")); // 영화 이름
                movieInfo.put("rankInten", obj.get("rankInten")); //전일대비 순위의 증감
                movieInfo.put("rankOldAndNew", obj.get("rankOldAndNew")); //랭킹 신규 진입했는지 아닌지 OLD:기준 NEW : 새로 진입함
                movieInfo.put("openDt", obj.get("openDt")); //개봉일
                movieInfo.put("audiAcc", obj.get("audiAcc")); //누적 관객수
                movieInfo.put("rank", obj.get("rank")); //누적 관객수
                mnList.put(obj.get("rnum"), movieInfo);

            }


////
            ObjectMapper mapper = new ObjectMapper();
            jsonInString = mapper.writeValueAsString(mnList);


        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body", e.getStatusText());
            System.out.println(e.toString());

        } catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body", "excpetion오류");
            System.out.println(e.toString());
        }
        return jsonInString;
    }
}
