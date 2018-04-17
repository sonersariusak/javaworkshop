package foriba.com.jws1order;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.OrderService;
import com.foriba.jws1.util.DateUtil;

@WebService(portName = "JWS1OrderPort", serviceName = "JWS1OrderService", endpointInterface = "foriba.com.jws1order.JWS1OrderPortType", targetNamespace = "http://com.foriba/JWS1Order", wsdlLocation = "META-INF/wsdl/foriba/com/jws1order/JWS1Order/JWS1Order.wsdl")
@Stateless
public class JWS1OrderPortTypeImplBean {
    private OrderService orderService;
	public  GetOrderListByOrderProductNameResponse getOrderListByOrderProductName(GetOrderListByOrderProductNameRequest parameter) {
	  return null;
	 }

	public  AddOrderResponse addOrder(AddOrderRequest parameter) throws Exception {
		AddOrderResponse aor=new AddOrderResponse();
		BigDecimal amnt=new BigDecimal(parameter.orderAmount);
		Jws1Order jws=new Jws1Order();
		jws.setOrderedProductName(parameter.orderedProductName);
		jws.setOrderAmount(amnt.setScale(2, BigDecimal.ROUND_UP));
		jws.setOrderDate(DateUtil.toDate(parameter.orderDate));
		jws.setOrderArrivalDate(DateUtil.toTimestamp(parameter.orderArrivalDate));
		jws.setOrderDetail(parameter.orderDetail);
		jws.setOrderInvoice(parameter.orderInvoice);
		orderService.addOrder(jws);
		aor.result="The order was saved successfully.";
		return aor;
	 }

	public  GetOrderByIDResponse getOrderByID(GetOrderByIDRequest parameter) {
	  return null;
	 }

	public  UpdateOrderResponse updateOrder(UpdateOrderRequest parameter) {
	  return null;
	 }

	public  UpdateAmountByOrderedProductNameResponse updateAmountByOrderedProductName(UpdateAmountByOrderedProductNameRequest parameter) {
	  return null;
	 }

	public  GetOrderListByOrderArrivalDateResponse getOrderListByOrderArrivalDate(GetOrderListByOrderArrivalDateRequest parameter) {
	  return null;
	 }

	public  GetOrderListByOrderDateResponse getOrderListByOrderDate(GetOrderListByOrderDateRequest parameter) {
	  return null;
	 }

	public  GetOrderListByOrderedProductNameByAmountResponse getOrderListByOrderedProductNameByAmount(GetOrderListByOrderedProductNameByAmountRequest parameter) {
	  return null;
	 }

	public  GetOrderListByAmountByOrderArrivalDateResponse getOrderListByAmountByOrderArrivalDate(GetOrderListByAmountByOrderArrivalDateRequest parameter) {
	  return null;
	 }

	public  GetOrderListByOrderedProductNameResponse getOrderListByOrderedProductName(GetOrderListByOrderedProductNameRequest parameter) {
	  return null;
	 }
}