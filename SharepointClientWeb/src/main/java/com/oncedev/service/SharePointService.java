package com.oncedev.service;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * @author Maksim Kanev
 */
@Service
public class SharePointService {

    private static final Logger log = LoggerFactory.getLogger(SharePointService.class);

    @Autowired
    private SharePointServiceCached serviceCached;

    @Autowired
    private RestTemplate restTemplate;

    public String performHttpRequest(HttpMethod method, String path) throws Exception {
        Long executionDateTime = serviceCached.parseExecutionDateTime(new Date());
        String securityToken = serviceCached.receiveSecurityToken(executionDateTime);
        List<String> cookies = serviceCached.getSignInCookies(securityToken);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Cookie", Joiner.on(';').join(cookies));
        RequestEntity<String> requestEntity = new RequestEntity<>(headers, method, new URI(serviceCached.completeURL(path)));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        String responseBody = responseEntity.getBody();
        log.debug(responseBody);
        return responseBody;
    }

    public String performHttpRequest(String path, String json, boolean isUpdate, boolean isWithDigest) throws Exception {
        Long executionDateTime = serviceCached.parseExecutionDateTime(new Date());
        String securityToken = serviceCached.receiveSecurityToken(executionDateTime);
        List<String> cookies = serviceCached.getSignInCookies(securityToken);
        String formDigestValue = serviceCached.getFormDigestValue(cookies);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Cookie", Joiner.on(';').join(cookies));
        headers.add("Content-type", "application/json;odata=verbose");
        if (isWithDigest) {
            headers.add("X-RequestDigest", formDigestValue);
        }
        if (isUpdate) {
            headers.add("X-HTTP-Method", "MERGE");
            headers.add("IF-MATCH", "*");
        }
        RequestEntity<String> requestEntity = new RequestEntity<>(json, headers, HttpMethod.POST, new URI(serviceCached.completeURL(path)));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        String responseBody = responseEntity.getBody();
        log.debug(responseBody);
        return responseBody;
    }

    public String attachFile(String path, byte[] file) throws Exception {
        Long executionDateTime = serviceCached.parseExecutionDateTime(new Date());
        String securityToken = serviceCached.receiveSecurityToken(executionDateTime);
        List<String> cookies = serviceCached.getSignInCookies(securityToken);
        String formDigestValue = serviceCached.getFormDigestValue(cookies);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Cookie", Joiner.on(';').join(cookies));
        headers.add("X-RequestDigest", formDigestValue);
        headers.add("content-length", String.valueOf(file.length));
        RequestEntity<byte[]> requestEntity = new RequestEntity<>(file, headers, HttpMethod.POST, new URI(serviceCached.completeURL(path)));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        String responseBody = responseEntity.getBody();
        log.debug(responseBody);
        return responseBody;
    }

}
