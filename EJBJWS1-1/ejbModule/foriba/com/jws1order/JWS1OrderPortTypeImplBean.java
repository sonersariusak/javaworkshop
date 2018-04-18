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
		return null;
	}

	public AddOrderResponse addOrder(AddOrderRequest parameter) {
		AddOrderResponse aor = new AddOrderResponse();
		BigDecimal amnt = new BigDecimal(parameter.orderAmount);
		Jws1Order jws = new Jws1Order();
		if(!StringUtil.stringNullorEmpty(parameter.orderedProductName)) {
			aor.result = "The value entered can not be null or empty.";
			return aor;
		}
		jws.setOrderedProductName(parameter.orderedProductName);
		jws.setOrderDate(DateUtil.toDate(parameter.orderDate));
		jws.setOrderAmount(amnt.setScale(2, BigDecimal.ROUND_UP));
		jws.setOrderDetail(parameter.orderDetail);
		jws.setOrderInvoice(parameter.orderInvoice);
		try {
			jws.setOrderArrivalDate(DateUtil.toTimestamp(parameter.orderArrivalDate));
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
		List<Jws1Order> orderIDList=new ArrayList<Jws1Order>();
		try {
			orderIDList=orderService.getOrderByID(parameter.id);
			for(int i = 0; i < orderIDList.size(); i++) {
				Jws1Order jws=orderIDList.get(i);
				Jws1OrderList list =new Jws1OrderList();
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
		return null;
	}

	public UpdateAmountByOrderedProductNameResponse updateAmountByOrderedProductName(UpdateAmountByOrderedProductNameRequest parameter) {
		return null;
	}

	public GetOrderListByOrderArrivalDateResponse getOrderListByOrderArrivalDate(GetOrderListByOrderArrivalDateRequest parameter) {
		return null;
	}

	public GetOrderListByOrderDateResponse getOrderListByOrderDate(GetOrderListByOrderDateRequest parameter) {
		return null;
	}

	public GetOrderListByOrderedProductNameByAmountResponse getOrderListByOrderedProductNameByAmount(GetOrderListByOrderedProductNameByAmountRequest parameter) {
		return null;
	}

	public GetOrderListByAmountByOrderArrivalDateResponse getOrderListByAmountByOrderArrivalDate(GetOrderListByAmountByOrderArrivalDateRequest parameter) {
		return null;
	}

	public GetOrderListByOrderedProductNameResponse getOrderListByOrderedProductName(GetOrderListByOrderedProductNameRequest parameter) {
		return null;
	}
}
