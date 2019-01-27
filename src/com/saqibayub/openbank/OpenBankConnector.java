package com.saqibayub.openbank;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saqibayub.openbank.domain.Transaction;
import com.saqibayub.openbank.domain.TransactionList;

@Component
public class OpenBankConnector {

	/*
	 * mock response
	 */
	/*
	public List<Transaction> getTransactions() {
		
		List<Transaction> transactions=new ArrayList<Transaction>();
		Transaction transaction1=new Transaction();
		transaction1.setId("id1");
		Account this_account=new Account();
		this_account.setId("this account id");
		Account other_account=new Account();
		other_account.setNumber("other account number ");
		TransactionDetail details=new TransactionDetail();
		details.setType("transaction Type");
		transaction1.setThis_account(this_account).setOther_account(other_account).setDetails(details);
		
		
		Transaction transaction2=new Transaction();
		transaction2.setId("id2");
		transaction2.setThis_account(this_account).setOther_account(other_account).setDetails(details);
		
		Transaction transaction3=new Transaction();
		transaction3.setId("id3");
		transaction3.setThis_account(this_account).setOther_account(other_account).setDetails(details);
		
		transactions.add(transaction1);
		transactions.add(transaction2);
		transactions.add(transaction3);
		return transactions;
	}
	*/
	public List<Transaction> getTransactions() {
		List<Transaction> transactions=new ArrayList<Transaction>();

		RestTemplate restTemplate = getRestTemplate();
		
		try {
			String token = getToken();
			HttpEntity httpEntity = new HttpEntity(new HttpHeaders()); 
			String endPoint="https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions";
			ResponseEntity<TransactionList> respEntity = restTemplate.exchange(endPoint, HttpMethod.GET,httpEntity,TransactionList.class);
			if(respEntity!=null) {
				TransactionList transactionList = respEntity.getBody();
				if(transactionList!=null) {
					transactions = transactionList.getTransactions();
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return transactions;
	}
	
	public String getToken() {
		/*
		  curl -X POST \
		  https://api.openbankproject.com/my/logins/direct \
		  -H 'authorization: DirectLogin username=\"saqibayub\", password=\"testRun\",  consumer_key=\"dzrhqv4f4hc2tzetg3mticxfa4bjmunlkms2\"' \
		  -H 'cache-control: no-cache' \
		  -H 'content-type: application/json' \
		  -H 'postman-token: e3cba6cc-223e-cdf1-f727-92f61f634ccb'
		 * */
		return null;
	}
	
	public RestTemplate getRestTemplate() {
		/*
		//Creating RestTemplate Interceptor
		List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = new ArrayList<ClientHttpRequestInterceptor>();
		clientHttpRequestInterceptors.add(rtInterceptor);
		*/
		//Setting BufferingClientHttpRequestFactory to ensure response body is always buffered
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);  	        
	    requestFactory.setConnectTimeout(25000);//FIXME move this number in property
	    requestFactory.setReadTimeout(25000);//FIXME move this number in property
	     
        BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(requestFactory);
        
        ObjectMapper objectMapperForUnknownProperties = new ObjectMapper()
        		.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
        		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        MappingJackson2HttpMessageConverter messageconverterForUnknownProperties = new MappingJackson2HttpMessageConverter();
        messageconverterForUnknownProperties.setObjectMapper(objectMapperForUnknownProperties);
        
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        
        List<HttpMessageConverter<?>> messageConverterlist = new ArrayList<>();
        messageConverterlist.add(0, messageconverterForUnknownProperties);
        messageConverterlist.add(1, formHttpMessageConverter);
        
		RestTemplate rt = new RestTemplate();     
//		rt.setInterceptors(clientHttpRequestInterceptors);
		rt.setRequestFactory(bufferingClientHttpRequestFactory);
		rt.setMessageConverters(messageConverterlist);
		
		return rt;
	}
}
