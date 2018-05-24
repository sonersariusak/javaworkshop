package foriba.com.jws1order;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.OrderService;
import com.foriba.jws1.util.DateUtil;
import com.foriba.jws1.util.StringUtil;


@WebService(portName = "JWS1OrderPort", serviceName = "JWS1OrderService", endpointInterface = "foriba.com.jws1order.JWS1OrderPortType", targetNamespace = "http://com.foriba/JWS1Order", wsdlLocation = "META-INF/wsdl/foriba/com/jws1order/JWS1Order/JWS1Order.wsdl")
@Stateless
public class JWS1OrderPortTypeImplBean {

	@EJB
	private OrderService orderService;

	public GetOrderListByOrderProductNameResponse getOrderListByOrderProductName(GetOrderListByOrderProductNameRequest parameter) {
		GetOrderListByOrderProductNameResponse response = new GetOrderListByOrderProductNameResponse();
		List<Jws1Order> orderProductNameList = new ArrayList<Jws1Order>();
		try {
			orderProductNameList = orderService.getOrderListByOrderProductName(parameter.getOrderProductName());
			for(int i = 0; i < orderProductNameList.size(); i++) {
				Jws1Order jws = orderProductNameList.get(i);
				Jws1OrderList list = new Jws1OrderList();
				list.setID(jws.getIdx());
				list.setOrderedProductName(jws.getOrderedProductName());
				list.setOrderDate(DateUtil.toXmlDate(jws.getOrderDate()));
				list.setOrderArrivalDate(DateUtil.toXmlDate(jws.getOrderArrivalDate()));
				list.setOrderAmount(jws.getOrderAmount().floatValue());
				list.setOrderDetail(jws.getOrderDetail());
				list.setSysVersion(jws.getSysVersion());
				list.setSysLastUpdate(DateUtil.toXmlDate(jws.getSysLastUpdate()));
				list.setOrderInvoice(jws.getOrderInvoice());
				response.getResult().add(list);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public AddOrderResponse addOrder(AddOrderRequest parameter) {
		AddOrderResponse aor = new AddOrderResponse();
		BigDecimal amnt = new BigDecimal(parameter.getOrderAmount());
		Jws1Order jws = new Jws1Order();
		if(!StringUtil.stringNullorEmpty(parameter.getOrderedProductName())) {
			aor.result = "The value entered can not be null or empty.";
			return aor;
		}
		jws.setOrderedProductName(parameter.getOrderedProductName());
		jws.setOrderDate(DateUtil.toDate(parameter.getOrderDate()));
		jws.setOrderAmount(amnt.setScale(2, BigDecimal.ROUND_UP));
		jws.setOrderDetail(parameter.getOrderDetail());
		jws.setOrderInvoice(parameter.getOrderInvoice());
		try {
			jws.setOrderArrivalDate(DateUtil.toTimestamp(parameter.getOrderArrivalDate()));
		}
		catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			orderService.addOrder(jws);
			aor.result = "The order was saved successfully.";
			return aor;
		}
		catch (Exception e) {
			e.getMessage();
		}
		return aor;


	}

	public GetOrderByIDResponse getOrderByID(GetOrderByIDRequest parameter) {

		GetOrderByIDResponse response = new GetOrderByIDResponse();
		List<Jws1Order> orderIDList = new ArrayList<Jws1Order>();
		try {
			orderIDList = orderService.getOrderByID(parameter.getID());
			for(int i = 0; i < orderIDList.size(); i++) {
				Jws1Order jws = orderIDList.get(i);
				Jws1OrderList list = new Jws1OrderList();
				list.setID(jws.getIdx());
				list.setOrderedProductName(jws.getOrderedProductName());
				list.setOrderDate(DateUtil.toXmlDate(jws.getOrderDate()));
				list.setOrderArrivalDate(DateUtil.toXmlDate(jws.getOrderArrivalDate()));
				list.setOrderAmount(jws.getOrderAmount().floatValue());
				list.setOrderDetail(jws.getOrderDetail());
				list.setSysVersion(jws.getSysVersion());
				list.setSysLastUpdate(DateUtil.toXmlDate(jws.getSysLastUpdate()));
				list.setOrderInvoice(jws.getOrderInvoice());
				response.getResult().add(list);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public UpdateOrderResponse updateOrder(UpdateOrderRequest parameter) {
		UpdateOrderResponse response = new UpdateOrderResponse();
		if(!StringUtil.stringNullorEmpty(parameter.getOrderedProductName())) {
			response.result = "The value entered can not be null or empty.";
			return response;
		}
		try {
			response.result = orderService.updateOrder(parameter.getID(), parameter.getOrderedProductName(),DateUtil.toTimestamp(parameter.getOrderArrivalDate()),DateUtil.toDate(parameter.getOrderDate()), parameter.getOrderAmount(), parameter.getOrderDetail(), parameter.getOrderInvoice().toString());
		}
		catch (Exception e) {
			response.result = e.getMessage();
			return response;
		}
		return response;
	}

	public UpdateAmountByOrderedProductNameResponse updateAmountByOrderedProductName(UpdateAmountByOrderedProductNameRequest parameter) {
		UpdateAmountByOrderedProductNameResponse response = new UpdateAmountByOrderedProductNameResponse();
		if(!StringUtil.stringNullorEmpty(parameter.getOrderedProductName())) {
			response.result = "The value entered can not be null or empty.";
			return response;
		}
		try {
			response.result = orderService.updateAmountByOrderedProductName(parameter.getOrderedProductName(), parameter.getOrderAmount());
		}
		catch (Exception e) {
			response.result = e.getMessage();
			return response;
		}
		return response;
	}

	public GetOrderListByOrderArrivalDateResponse getOrderListByOrderArrivalDate(GetOrderListByOrderArrivalDateRequest parameter) {
		GetOrderListByOrderArrivalDateResponse response = new GetOrderListByOrderArrivalDateResponse();
		List<Jws1Order> orderArrivalDateList = new ArrayList<Jws1Order>();
		try {
			orderArrivalDateList = orderService.getOrderListByOrderArrivalDate(DateUtil.toTimestamp(parameter.getStartDate()), DateUtil.toTimestamp(parameter.getEndDate()));
			for(int i = 0; i < orderArrivalDateList.size(); i++) {
				Jws1Order jws = orderArrivalDateList.get(i);
				Jws1OrderList list = new Jws1OrderList();
				list.setID(jws.getIdx());
				list.setOrderedProductName(jws.getOrderedProductName());
				list.setOrderDate(DateUtil.toXmlDate(jws.getOrderDate()));
				list.setOrderArrivalDate(DateUtil.toXmlDate(jws.getOrderArrivalDate()));
				list.setOrderAmount(jws.getOrderAmount().floatValue());
				list.setOrderDetail(jws.getOrderDetail());
				list.setSysVersion(jws.getSysVersion());
				list.setSysLastUpdate(DateUtil.toXmlDate(jws.getSysLastUpdate()));
				list.setOrderInvoice(jws.getOrderInvoice());
				response.getResult().add(list);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public GetOrderListByOrderDateResponse getOrderListByOrderDate(GetOrderListByOrderDateRequest parameter) {
		GetOrderListByOrderDateResponse response = new GetOrderListByOrderDateResponse();
		List<Jws1Order> orderDateList = new ArrayList<Jws1Order>();
		try {
			orderDateList = orderService.getOrderListByOrderDate(DateUtil.toDate(parameter.getDate()));
			for(int i = 0; i < orderDateList.size(); i++) {
				Jws1Order jws = orderDateList.get(i);
				Jws1OrderList list = new Jws1OrderList();
				list.setID(jws.getIdx());
				list.setOrderedProductName(jws.getOrderedProductName());
				list.setOrderDate(DateUtil.toXmlDate(jws.getOrderDate()));
				list.setOrderArrivalDate(DateUtil.toXmlDate(jws.getOrderArrivalDate()));
				list.setOrderAmount(jws.getOrderAmount().floatValue());
				list.setOrderDetail(jws.getOrderDetail());
				list.setSysVersion(jws.getSysVersion());
				list.setSysLastUpdate(DateUtil.toXmlDate(jws.getSysLastUpdate()));
				list.setOrderInvoice(jws.getOrderInvoice());
				response.getResult().add(list);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public GetOrderListByOrderedProductNameByAmountResponse getOrderListByOrderedProductNameByAmount(GetOrderListByOrderedProductNameByAmountRequest parameter) {
		GetOrderListByOrderedProductNameByAmountResponse response = new GetOrderListByOrderedProductNameByAmountResponse();
		List<Jws1Order> orderByProductNameByAmount = new ArrayList<Jws1Order>();
		try {
			orderByProductNameByAmount = orderService.getOrderListByOrderedProductNameByAmount(parameter.getOrderedProductName(), parameter.getOrderAmount());
			for(int i = 0; i < orderByProductNameByAmount.size(); i++) {
				Jws1Order jws = orderByProductNameByAmount.get(i);
				Jws1OrderList list = new Jws1OrderList();
				list.setID(jws.getIdx());
				list.setOrderedProductName(jws.getOrderedProductName());
				list.setOrderDate(DateUtil.toXmlDate(jws.getOrderDate()));
				list.setOrderArrivalDate(DateUtil.toXmlDate(jws.getOrderArrivalDate()));
				list.setOrderAmount(jws.getOrderAmount().floatValue());
				list.setOrderDetail(jws.getOrderDetail());
				list.setSysVersion(jws.getSysVersion());
				list.setSysLastUpdate(DateUtil.toXmlDate(jws.getSysLastUpdate()));
				list.setOrderInvoice(jws.getOrderInvoice());
				response.getResult().add(list);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public GetOrderListByAmountByOrderArrivalDateResponse getOrderListByAmountByOrderArrivalDate(GetOrderListByAmountByOrderArrivalDateRequest parameter) {
		GetOrderListByAmountByOrderArrivalDateResponse response = new GetOrderListByAmountByOrderArrivalDateResponse();
		List<Jws1Order> orderArrivalDatebyAmountList = new ArrayList<Jws1Order>();
		try {
			orderArrivalDatebyAmountList =
					orderService.getOrderListByAmountByOrderArrivalDate(parameter.getOrderAmount(), DateUtil.toTimestamp(parameter.getStartDate()), DateUtil.toTimestamp(parameter.getEndDate()));
			for(int i = 0; i < orderArrivalDatebyAmountList.size(); i++) {
				Jws1Order jws = orderArrivalDatebyAmountList.get(i);
				Jws1OrderList list = new Jws1OrderList();
				list.setID(jws.getIdx());
				list.setOrderedProductName(jws.getOrderedProductName());
				list.setOrderDate(DateUtil.toXmlDate(jws.getOrderDate()));
				list.setOrderArrivalDate(DateUtil.toXmlDate(jws.getOrderArrivalDate()));
				list.setOrderAmount(jws.getOrderAmount().floatValue());
				list.setOrderDetail(jws.getOrderDetail());
				list.setSysVersion(jws.getSysVersion());
				list.setSysLastUpdate(DateUtil.toXmlDate(jws.getSysLastUpdate()));
				list.setOrderInvoice(jws.getOrderInvoice());
				response.getResult().add(list);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public GetOrderListByOrderedProductNameResponse getOrderListByOrderedProductName(GetOrderListByOrderedProductNameRequest parameter) {
		GetOrderListByOrderedProductNameResponse response = new GetOrderListByOrderedProductNameResponse();
		List<Jws1Order> orderedProductNameList = new ArrayList<Jws1Order>();
		try {
			orderedProductNameList = orderService.getOrderListByOrderedProductName(parameter.getOrderProductName());
			for(int i = 0; i < orderedProductNameList.size(); i++) {
				Jws1Order jws = orderedProductNameList.get(i);
				Jws1OrderList list = new Jws1OrderList();
				list.setID(jws.getIdx());
				list.setOrderedProductName(jws.getOrderedProductName());
				list.setOrderDate(DateUtil.toXmlDate(jws.getOrderDate()));
				list.setOrderArrivalDate(DateUtil.toXmlDate(jws.getOrderArrivalDate()));
				list.setOrderAmount(jws.getOrderAmount().floatValue());
				list.setOrderDetail(jws.getOrderDetail());
				list.setSysVersion(jws.getSysVersion());
				list.setSysLastUpdate(DateUtil.toXmlDate(jws.getSysLastUpdate()));
				list.setOrderInvoice(jws.getOrderInvoice());
				response.getResult().add(list);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
