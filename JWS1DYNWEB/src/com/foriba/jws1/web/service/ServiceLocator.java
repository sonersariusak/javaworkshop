/**
 * 
 * Created by Murat Demir 
 *
 */
package com.foriba.jws1.web.service;

import java.text.MessageFormat;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author murat.demir
 *
 * 
 * Apr 16, 2015 - 6:47:29 AM
 *
 *
 */
public class ServiceLocator {

	private static String CORE_LOOKUP = "ejb:/appName=sap.com/fit.earsiv.core.app, beanName={0}Bean, interfaceName={1}";
	private static String CORE_LOOKUPIMPL = "ejb:/appName=sap.com/fit.earsiv.core.app, beanName={0}Impl, interfaceName={1}";
	private ServiceLocator() {}

	public static <T> T getCoreService(Class<?> serviceClass) {
		try {
			return InitialContext.doLookup(MessageFormat.format(CORE_LOOKUP, serviceClass.getSimpleName(), serviceClass.getName()));
		} catch (NamingException e) {
			throw new RuntimeException(MessageFormat.format("Could not find any service bound to the name \"{0}\" in JNDI.", new Object[] { serviceClass }), e);
		}
	}
	
	public static <T> T getCoreServiceImpl(Class<?> serviceClass) {
		try {
			return InitialContext.doLookup(MessageFormat.format(CORE_LOOKUPIMPL, serviceClass.getSimpleName(), serviceClass.getName()));
		} catch (NamingException e) {
			throw new RuntimeException(MessageFormat.format("Could not find any service bound to the name \"{0}\" in JNDI.", new Object[] { serviceClass }), e);
		}
	}

}
