package com.oncedev.service.conf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.xml.xpath.XPathExpressionFactoryBean;

/**
 * @author Maksim Kanev
 */
@Configuration
public class SharePointConfiguration {

    @Bean
    public XPathExpressionFactoryBean securityTokenExpressionFactoryBean() {
        XPathExpressionFactoryBean xPathExpressionFactoryBean = new XPathExpressionFactoryBean();
        xPathExpressionFactoryBean.setExpression("/S:Envelope/S:Body/wst:RequestSecurityTokenResponse/wst:RequestedSecurityToken/wsse:BinarySecurityToken");

        Map<String, String> namespaces = new HashMap<String, String>();
        namespaces.put("S", "http://www.w3.org/2003/05/soap-envelope");
        namespaces.put("wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        namespaces.put("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        namespaces.put("wsa", "http://www.w3.org/2005/08/addressing");
        namespaces.put("wst", "http://schemas.xmlsoap.org/ws/2005/02/trust");
        xPathExpressionFactoryBean.setNamespaces(namespaces);
        return xPathExpressionFactoryBean;
    }
}