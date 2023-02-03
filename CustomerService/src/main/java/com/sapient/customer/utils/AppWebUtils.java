/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sapient.customer.annotations.AppLocaleMessage;
import com.sapient.customer.annotations.EndpointMetadata;
import com.sapient.customer.consts.MiscConstants;
import com.sapient.customer.models.ResponseDTO;

/**
 * Application Web Utility class to provide utilities which are commonly used
 * for different purpose.
 *
 * @author Sandeep Kumar
 */
public class AppWebUtils {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(AppWebUtils.class.getName());

	/**
	 * Instantiates a new app web utils.
	 */
	private AppWebUtils() {}
	
	/**
	 * It provides current HttpServletRequest .
	 *
	 * @return Returns HttpServletRequest object wrapped into Optional
	 */
	public static Optional<HttpServletRequest> getCurrentHttpRequest() {
	    return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
	        .filter(requestAttributes -> ServletRequestAttributes.class.isAssignableFrom(requestAttributes.getClass()))
	        .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
	        .map(ServletRequestAttributes::getRequest);
	}

	/**
	 * It provides current HttpServletRequest .
	 *
	 * @return Returns HttpServletRequest object
	 */
	public static HttpServletRequest getCurrentServletHttpRequest() {
	    return (null != RequestContextHolder.getRequestAttributes())?((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest():null;
	}
	
	
	/**
	 * Obtain message for the given key by Using current request's locale.
	 *
	 * @param messageSource  Message source object
	 * @param key            Key for which localized message obtained
	 * @param defaultMessage Default Message if localized message not available,
	 *                       Could be null
	 * @param args           Object list to fill into the message if any argument
	 *                       defined into message by {0}. {1} etc. Could be null
	 * @return Return string
	 */
	public static String getMessage(MessageSource messageSource, String key, String defaultMessage, Object[] args) {
		HttpServletRequest hsr = getCurrentServletHttpRequest();
		return messageSource.getMessage(key, args, (null != defaultMessage?defaultMessage:key), (null != hsr)?hsr.getLocale():null);
	}
	
	/**
	 * Set localized message into object.
	 *
	 * @param               <T1> the generic type
	 * @param messageSource MessageSource object
	 * @param t1            Input Object
	 */
	public static <T1> void initMessages(MessageSource messageSource, T1 t1) {
		if(null == t1) {
			return;
		}
		for(Field field:t1.getClass().getDeclaredFields()) {
			AppLocaleMessage alm = field.getAnnotation(AppLocaleMessage.class);
			if(null != alm) {
				String key = getKey(alm, t1);
				String code = (null != alm.code() && !MiscConstants.EMPTY.equals(alm.code()))?alm.code():null;
				String valueTxt = getValueTxt(alm, t1);
				String defaultValue = getDefaultValue(alm, t1);
				String codeValue = getValueAsStringFromObject(t1, code);
				StringBuilder keyBuilder = getKeyBuilder(key, codeValue, valueTxt);
				setValueInObject(messageSource, field, keyBuilder, defaultValue, t1);
			}
		}
	}
	
	/**
	 * It obtain the key from various source of input.
	 *
	 * @param     <T1> Generic type object where annotation has been used
	 * @param alm Annotation object as input
	 * @param t1  Object on which annotation has been implemented
	 * @return Returns key by evaluating business logic
	 */
	private static <T1> String getKey(AppLocaleMessage alm, T1 t1) {
		String key = (null != alm.key() && !MiscConstants.EMPTY.equals(alm.key()) && !MiscConstants.BLANK.equals(alm.key()))?alm.key():null;
		if(null == key) {
			String keyField = (null != alm.keyField() && !MiscConstants.EMPTY.equals(alm.keyField()) && !MiscConstants.BLANK.equals(alm.keyField()))?alm.keyField():null;
			if(null != keyField) {
				key = getValueAsStringFromObject(t1, keyField);
			}
		}
		if(null == key) {
			key = t1.getClass().getCanonicalName();
		}
		return key;
	}
	
	/**
	 * It obtain the Value Text from various source of input.
	 *
	 * @param     <T1> Generic type object where annotation has been used
	 * @param alm Annotation object as input
	 * @param t1  Object on which annotation has been implemented
	 * @return Returns value text by evaluating business logic
	 */
	private static <T1> String getValueTxt(AppLocaleMessage alm, T1 t1) {
		String value = (null != alm.value() && !MiscConstants.EMPTY.equals(alm.value()) && !MiscConstants.BLANK.equals(alm.value()))?alm.value():null;
		String valueTxt = null;
		if(null != value) {
			valueTxt = getValueAsStringFromObject(t1, value);
		}
		return valueTxt;
	}
	
	/**
	 * It obtain the default value from various source of input.
	 *
	 * @param     <T1> Generic type object where annotation has been used
	 * @param alm Annotation object as input
	 * @param t1  Object on which annotation has been implemented
	 * @return Returns default value by evaluating business logic
	 */
	private static <T1> String getDefaultValue(AppLocaleMessage alm, T1 t1) {
		String defaultValue = (null != alm.defaultValue() && !MiscConstants.EMPTY.equals(alm.defaultValue()))?alm.defaultValue():null;
		String defaultValueField = (null != alm.defaultValueField() && !MiscConstants.EMPTY.equals(alm.defaultValueField()) && !MiscConstants.BLANK.equals(alm.defaultValueField()))?alm.defaultValueField():null;
		if(null != defaultValueField) {
			defaultValue = getValueAsStringFromObject(t1, defaultValueField);
		}
		return defaultValue;
	}
	
	/**
	 * It generates the complete key by using key, code value and value text.
	 *
	 * @param key       Input Key
	 * @param codeValue Input Code value
	 * @param valueTxt  Input calculated value text
	 * @return Return the complete set of key to obatin message from message source
	 */
	private static StringBuilder getKeyBuilder(String key, String codeValue, String valueTxt) {
		StringBuilder keyBuilder = new StringBuilder(key);
		if(null != codeValue) {
			keyBuilder.append(MiscConstants.DOT);
			keyBuilder.append(codeValue);
		}
		if(null != valueTxt) {
			keyBuilder.append(MiscConstants.DOT);
			keyBuilder.append(valueTxt);
		}
		return keyBuilder;
	}

	/**
	 * It set the calculated message to the object.
	 *
	 * @param               <T1> Type of object where annotation has been used
	 * @param messageSource Message source object form spring
	 * @param field         Field on which annotation available
	 * @param keyBuilder    Generated key based upon input from Annotation
	 * @param defaultValue  Default value of key
	 * @param t1            Object in which localization happen
	 */
	private static <T1> void setValueInObject(MessageSource messageSource, Field field, StringBuilder keyBuilder, String defaultValue, T1 t1) {
		String messageValue = getMessage(messageSource, keyBuilder.toString(), defaultValue, null);
		if(null != messageValue) {
			try {
				field.setAccessible(true);
				field.set(t1, messageValue);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				LOGGER.info("Field is not accessible - " + t1.getClass().getName() + ":" + field.getName());
			}
		}
	}
	
	
	/**
	 * Helper method for initMessages.
	 *
	 * @param           <T1> the generic type
	 * @param t1        Input Object
	 * @param fieldName Name of filed
	 * @return Returns String
	 */
	private static <T1> String getValueAsStringFromObject(T1 t1, String fieldName) {
		String retVal = null;
		if(null != t1 && null != fieldName) {
			Field field = null;
			try {
				field = t1.getClass().getDeclaredField(fieldName);
				if(null != field) {
					field.setAccessible(true);
					retVal = getKeysInString(field, t1);
				}
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				LOGGER.info("Ignorable Exception generated");
			}
		}
		return retVal;
	}

	/**
	 * Helper method - to get the field value passed as generic into the method as
	 * String.
	 *
	 * @param       <T1> Type from which fields have to be checked
	 * @param field Field object as input
	 * @param t1    Object from where value has to be obtained
	 * @return Returns the Key name as string
	 * @throws IllegalAccessException It will be generated when any access issue
	 *                                happen
	 */
	private static <T1> String getKeysInString(Field field, T1 t1) throws IllegalAccessException {
		String retVal = null;
		switch(field.getType().getName().toLowerCase()) {
			case MiscConstants.BYTE:
				byte byteVar = field.getByte(t1);
				retVal = "" + byteVar;
				break;
			case MiscConstants.SHORT:
				short shortVar = field.getShort(t1);
				retVal = "" + shortVar;
				break;
			case MiscConstants.CHAR:
				char charVar = field.getChar(t1);
				retVal = "" + charVar;
				break;
			case MiscConstants.INT:
				int intVar = field.getInt(t1);
				retVal = "" + intVar;
				break;
			case MiscConstants.FLOAT: 
				float floatVar = field.getFloat(t1);
				retVal = "" + floatVar;
				break;
			case MiscConstants.LONG:
				long longVar = field.getLong(t1);
				retVal = "" + longVar;
				break;
			case MiscConstants.DOUBLE:
				double doubleVar = field.getDouble(t1);
				retVal = "" + doubleVar;
				break;
			case MiscConstants.BOOLEAN:
				boolean booleanVar = field.getBoolean(t1);
				retVal = "" + booleanVar;
				break;
			default:
				Object ob = field.get(t1);
				if(null != ob) {
					retVal = ob.toString();
				}
		}
		return retVal;
	}
	
	
	/**
	 * It provides system branch for the current request.
	 *
	 * @return Returns branch code
	 */
	public static String getSystemBranchCode() {
		HttpServletRequest hsr = getCurrentServletHttpRequest();
		return (null != hsr)?hsr.getHeader("branch"):null;
	}
	
	
	/**
	 * This method is a utility to write object as string.
	 *
	 * @param ob Takes parameter as object which has to be written as string
	 * @return Returns the string formation of the object
	 * @throws JsonProcessingException Throws exception as any occured
	 */
	public static String writeObjectAsString(Object ob) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    return ow.writeValueAsString(ob);
	}

	/**
	 * Builds the response.
	 *
	 * @param <T> the generic type
	 * @param message the message
	 * @param status the status
	 * @param data the data
	 * @return the response entity
	 */
	public static <T> ResponseEntity<ResponseDTO<T>> buildResponse(String message, HttpStatus status, T data) {
		return new ResponseEntity<ResponseDTO<T>>(new ResponseDTO<T>(data, message, status), status);
	}
	
	/**
	 * Obtain end point metadata.
	 *
	 * @return the endpoint metadata
	 */
	public static EndpointMetadata obtainEndPointMetadata() {
		EndpointMetadata methodMetadata = null;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		for(StackTraceElement element:stackTraceElements) {
			methodMetadata = obtainEndPointMetadata(element, classLoader);
			if(null != methodMetadata) {
				break;
			}
		}
		return methodMetadata;
	}
	
	/**
	 * Obtain end point metadata.
	 *
	 * @param element     the element
	 * @param classLoader the class loader
	 * @return the endpoint metadata
	 */
	public static EndpointMetadata obtainEndPointMetadata(StackTraceElement element, ClassLoader classLoader) {
		@SuppressWarnings("rawtypes")
		Class tempClass = null;
		try {
			tempClass = classLoader.loadClass(element.getClassName());
			//tempClass = Class.forName(element.getClassName());
		} catch (ClassNotFoundException e) {
			//Need need to capture this exception
		}
		if(null != tempClass) {
			@SuppressWarnings("unchecked")
			RestController restControllerMetadata = (RestController) tempClass.getAnnotation(RestController.class);
			@SuppressWarnings("unchecked")
			Controller controllerMetadata = (Controller) tempClass.getAnnotation(Controller.class);
			if(null != restControllerMetadata || null != controllerMetadata) {
				Method[] methods = tempClass.getDeclaredMethods();
				for(Method method:methods) {
					if(method.getName().equals(element.getMethodName())) {
						method.setAccessible(true);
						return method.getAnnotation(EndpointMetadata.class);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * From string.
	 *
	 * @param date the date
	 * @return the local date
	 */
	public static LocalDate fromString(String date) {
		if(null != date && !date.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			formatter = formatter.withLocale( getCurrentServletHttpRequest().getLocale() );
			return LocalDate.parse(date, formatter);	
		}
		return null;
	}
	
	
}