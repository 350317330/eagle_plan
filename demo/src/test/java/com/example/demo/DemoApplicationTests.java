package com.example.demo;


import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    /*@Test
    void test() {
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("email", "844072586@qq.com");
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers, map);
        String s = restTemplate.postForObject("", entity, String.class);
        System.out.println(s);
    }

    @Test
    void test1(){
    }*/

}
