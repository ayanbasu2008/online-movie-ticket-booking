/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.movie.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.sapient.movie.consts.SpecificationConstants;


/**
 * This class has been created for providing utility methods used for
 * Specification based column filtering.
 *
 * @author Sandeep Kumar
 */
public class SpecificationUtils {

	/**
	 * Instantiates a new specification utils.
	 */
	private SpecificationUtils() {}

	/**
	 * Builds the specification.
	 *
	 * @param <T> the generic type
	 * @param query the query
	 * @param externalExpressionList the external expression list
	 * @return the specification
	 */
	public static <T> Specification<T> buildSpecification(String query, List<Expression<? extends Comparable<?>>> externalExpressionList) {
		List<Expression<? extends Comparable<?>>> expressionList = new ArrayList<>();
		if(null != externalExpressionList) {
			expressionList.addAll(externalExpressionList);
		}
		expressionList.addAll(parseQuery(query));
		return new CustomSpecification<>(expressionList);
	}
	
	/**
	 * The Class CustomSpecification.
	 *
	 * @param <T> the generic type
	 */
	@SuppressWarnings("serial")
	public static class CustomSpecification<T> implements Specification<T> {
		
		/** The expressions. */
		private List<Expression<? extends Comparable<?>>> expressions;

		/**
		 * Instantiates a new custom specification.
		 *
		 * @param expressions the expressions
		 */
		public CustomSpecification(List<Expression<? extends Comparable<?>>> expressions) {
			super();
			this.expressions = expressions;
		}

		/**
		 * Gets the expressions.
		 *
		 * @return the expressions
		 */
		public List<Expression<? extends Comparable<?>>> getExpressions() {
			return expressions;
		}

		/**
		 * Sets the expressions.
		 *
		 * @param expressions the new expressions
		 */
		public void setExpressions(List<Expression<? extends Comparable<?>>> expressions) {
			this.expressions = expressions;
		}

		/* (non-Javadoc)
		 * @see org.springframework.data.jpa.domain.Specification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery, javax.persistence.criteria.CriteriaBuilder)
		 */
		@Override
		public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			Predicate result =  cb.conjunction();
			for(Expression<? extends Comparable<?>> expression:expressions) {
				if(null != expression && null != expression.getField() && null != expression.getOperator() && (null != expression.getValue() || null != expression.getValues())) {
					Predicate tmpPredicate = handleExpression(root, cb, expression);
					if(SpecificationConstants.JOIN_AND.equalsIgnoreCase(expression.getJoin())) {
						result = cb.and(result, tmpPredicate);
					}
					else if(SpecificationConstants.JOIN_OR.equalsIgnoreCase(expression.getJoin())) {
						result = cb.or(result, tmpPredicate);
					}
				}
			}
			return result;
		}
	}
	
	/**
	 * Handle expression.
	 *
	 * @param <T> the generic type
	 * @param <E> the element type
	 * @param root the root
	 * @param cb the cb
	 * @param expression the expression
	 * @return the predicate
	 */
	private static <T,E extends Comparable<E>> Predicate handleExpression(Root<T> root, CriteriaBuilder cb, Expression<E> expression) {
		Predicate tempPredicate = null;
		switch(expression.getOperator()) {
			case SpecificationConstants.OPERATOR_EQUAL:
				tempPredicate = cb.equal(root.get(expression.getField()), expression.getValue());
				break;
			case SpecificationConstants.OPERATOR_NOTEQUAL:
				tempPredicate = cb.notEqual(root.get(expression.getField()), expression.getValue());
				break;
			case SpecificationConstants.OPERATOR_CONTAIN:
				tempPredicate = cb.like(root.get(expression.getField()), SpecificationConstants.DATA_PERCENT_SYMBOL + expression.getValue() + SpecificationConstants.DATA_PERCENT_SYMBOL);
				break;
			case SpecificationConstants.OPERATOR_START_WITH:
				tempPredicate = cb.like(root.get(expression.getField()), expression.getValue() + SpecificationConstants.DATA_PERCENT_SYMBOL);
				break;
			case SpecificationConstants.OPERATOR_ENDS_WITH:
				tempPredicate = cb.like(root.get(expression.getField()), SpecificationConstants.DATA_PERCENT_SYMBOL + expression.getValue());
				break;
			case SpecificationConstants.OPERATOR_GREATER_THAN:
				tempPredicate = cb.greaterThan(root.get(expression.getField()), expression.getValue());
				break;
			case SpecificationConstants.OPERATOR_LESS_THAN:
				tempPredicate = cb.lessThan(root.get(expression.getField()), expression.getValue());
				break;
			case SpecificationConstants.OPERATOR_GREATER_THAN_EQUAL:
				tempPredicate = cb.greaterThanOrEqualTo(root.get(expression.getField()), expression.getValue());
				break;
			case SpecificationConstants.OPERATOR_LESS_THAN_EQUAL:
				tempPredicate = cb.lessThanOrEqualTo(root.get(expression.getField()), expression.getValue());
				break;
			case SpecificationConstants.OPERATOR_IN:
				tempPredicate = root.get(expression.getField()).in(expression.getValues());
				break;
			default: 
				//Do nothing - Not required as per business logic but SonarLint gives log
		}
		return tempPredicate;
	}

	/**
	 * Parses the query.
	 *
	 * @param query the query
	 * @return the list< expression<? extends comparable<?>>>
	 */
	private static List<Expression<? extends Comparable<?>>> parseQuery(String query) {
		List<Expression<? extends Comparable<?>>> retVal = new ArrayList<>();
		query = (null == query || query.isEmpty())?SpecificationConstants.BLANK:query;
		Matcher matcher = Pattern.compile(SpecificationConstants.SPLIT_PATTERN_STRING + SpecificationConstants.PIPE + SpecificationConstants.SPLIT_PATTERN_NUMBER).matcher(query);
		while(matcher.find()) {
			String group = matcher.group();
			String field = matcher.group(1);
			String operator = matcher.group(2);
			String value = matcher.group(3);
			String join = (operator.contains(SpecificationConstants.COLON))?operator.split(SpecificationConstants.COLON)[1]:SpecificationConstants.JOIN_AND;
			operator = (operator.contains(SpecificationConstants.COLON))?operator.split(SpecificationConstants.COLON)[0]:operator;
			
			if(group.equals(SpecificationConstants.SPLIT_PATTERN_NUMBER)) {
				retVal.add(new Expression<Double>(field, operator, join, new Double(value), null));
			}
			else {
				if(field.contains(SpecificationConstants.COLON)) {
					String fieldName = field.split(SpecificationConstants.COLON)[0];
					String type = field.split(SpecificationConstants.COLON)[1];
					try {
						if(type.equalsIgnoreCase(SpecificationConstants.BOOLEAN)) {
							retVal.add(new Expression<Boolean>(fieldName, operator, join, new Boolean(value), null));
						}
						else if(type.equalsIgnoreCase(SpecificationConstants.BYTE)) {
							retVal.add(new Expression<Byte>(fieldName, operator, join, new Byte(value), null));
						}
						else if(type.equalsIgnoreCase(SpecificationConstants.CHARACTER)) {
							retVal.add(new Expression<Character>(fieldName, operator, join, new Character(value.charAt(0)), null));
						}
						else if(type.equalsIgnoreCase(SpecificationConstants.SHORT)) {
							retVal.add(new Expression<Short>(fieldName, operator, join, new Short(value), null));
						}
						else if(type.equalsIgnoreCase(SpecificationConstants.INTEGER)) {
							retVal.add(new Expression<Integer>(fieldName, operator, join, new Integer(value), null));
						}
						else if(type.equalsIgnoreCase(SpecificationConstants.LONG)) {
							retVal.add(new Expression<Long>(fieldName, operator, join, new Long(value), null));
						}
						else if(type.equalsIgnoreCase(SpecificationConstants.FLOAT)) {
							retVal.add(new Expression<Float>(fieldName, operator, join, new Float(value), null));
						}
						else if(type.equalsIgnoreCase(SpecificationConstants.DOUBLE)) {
							retVal.add(new Expression<Double>(fieldName, operator, join, new Double(value), null));
						}
						else if(type.equalsIgnoreCase(SpecificationConstants.BIGINTEGER)) {
							retVal.add(new Expression<BigInteger>(fieldName, operator, join, new BigInteger(value), null));
						}
						else if(type.equalsIgnoreCase(SpecificationConstants.BIGDECIMAL)) {
							retVal.add(new Expression<BigDecimal>(fieldName, operator, join, new BigDecimal(value), null));
						}
						else {
							retVal.add(new Expression<String>(fieldName, operator, join, new String(value), null));
						}
					}
					catch(Exception ex) {
						retVal.add(new Expression<String>(field, operator, join, value, null));
					}
				}
				else {
					retVal.add(new Expression<String>(field, operator, join, value, null));
				}
			}
		}
		return retVal;
	}
	
	/**
	 * The Class Expression.
	 *
	 * @param <E> the element type
	 */
	public static class Expression<E extends Comparable<E>> {
		
		/** The field. */
		private String field;
		
		/** The operator. */
		private String operator;
		
		/** The join. */
		private String join;
		
		/** The value. */
		private E value;
		
		/** The values. */
		private List<E> values;

		/**
		 * Instantiates a new expression.
		 */
		public Expression() {
			super();
		}

		/**
		 * Instantiates a new expression.
		 *
		 * @param field the field
		 * @param operator the operator
		 * @param join the join
		 * @param value the value
		 * @param values the values
		 */
		public Expression(String field, String operator, String join, E value, List<E> values) {
			super();
			this.field = field;
			this.operator = operator;
			this.join = join;
			this.value = value;
			this.values = values;
		}

		/**
		 * Gets the field.
		 *
		 * @return the field
		 */
		public String getField() {
			return field;
		}

		/**
		 * Sets the field.
		 *
		 * @param field the new field
		 */
		public void setField(String field) {
			this.field = field;
		}

		/**
		 * Gets the operator.
		 *
		 * @return the operator
		 */
		public String getOperator() {
			return operator;
		}

		/**
		 * Sets the operator.
		 *
		 * @param operator the new operator
		 */
		public void setOperator(String operator) {
			this.operator = operator;
		}

		/**
		 * Gets the join.
		 *
		 * @return the join
		 */
		public String getJoin() {
			return join;
		}

		/**
		 * Sets the join.
		 *
		 * @param join the new join
		 */
		public void setJoin(String join) {
			this.join = join;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public E getValue() {
			return value;
		}

		/**
		 * Sets the value.
		 *
		 * @param value the new value
		 */
		public void setValue(E value) {
			this.value = value;
		}

		/**
		 * Gets the values.
		 *
		 * @return the values
		 */
		public List<E> getValues() {
			return values;
		}

		/**
		 * Sets the values.
		 *
		 * @param values the new values
		 */
		public void setValues(List<E> values) {
			this.values = values;
		}
	}
}
