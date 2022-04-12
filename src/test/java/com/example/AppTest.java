package com.example;

import static org.junit.Assert.assertTrue;

import com.adaptavist.tm4j.junit.annotation.TestCase;

//import com.smartbear.zephyrscale.junit.annotation.TestCase;

import org.junit.Test;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws Exception
     */
    @Test
    @TestCase(key = "JQA-T1")
    public void SignIn_CMS_User_Successfully() throws Exception
    {
        //assertTrue( true );
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("email", "admin@admin.com");
        map.add("password", "Admin123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map,
                headers);
        JSONObject jsonObject = null;

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    "https://api.dev.elkaso.app/auth/signin", HttpMethod.POST, entity,
                    String.class);

            if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
                try {
                    jsonObject = new JSONObject(responseEntity.getBody());
                    Assert.assertEquals(201, responseEntity.getStatusCodeValue());
                    System.out.print(jsonObject);
                } catch (JSONException e) {
                    throw new RuntimeException("JSONException occurred");
                }
            }
        } catch (final HttpClientErrorException httpClientErrorException) {
            throw new Exception();
        } catch (HttpServerErrorException httpServerErrorException) {
            throw new Exception(httpServerErrorException);
        } catch (Exception exception) {
            throw new Exception(exception);
        }
    }
}
