package com.foriba.jws1.tester;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.Date;

import javax.xml.ws.BindingProvider;
import org.junit.Test;
import foriba.com.jws1order.AddOrderRequest;
import foriba.com.jws1order.AddOrderResponse;
import foriba.com.jws1order.JWS1OrderPortType;
import foriba.com.jws1order.JWS1OrderService;


public class JUnitTestCase {
	@Test
	public void test() {

		JWS1OrderService jos;
		try {
			jos = new JWS1OrderService();
			JWS1OrderPortType port = jos.getJWS1OrderPort();
			String endpointURL = "http://192.20.107.202:50000/JWS1OrderService/JWS1OrderPortTypeImplBean";
			BindingProvider bp = (BindingProvider) port;
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
			try {
				AddOrderRequest request = new AddOrderRequest();
				request.setOrderedProductName("Deneme");
				request.setOrderDate(DateUtil.toXmlDate(new Date()));
				request.setOrderArrivalDate(DateUtil.toXmlDate(new Date()));
				request.setOrderDetail("Deneme");
				request.setOrderInvoice(null);
				request.setOrderAmount(4000);
				try {
					AddOrderResponse response = port.addOrder(request);
					assertEquals("The order was saved successfully.", response.getResult());
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
