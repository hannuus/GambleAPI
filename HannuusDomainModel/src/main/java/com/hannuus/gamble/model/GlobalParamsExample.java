package com.hannuus.gamble.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GlobalParamsExample {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	protected Integer limitStart;

	protected Integer limitEnd;

	public GlobalParamsExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	public void setLimitStart(Integer limitStart) {
		this.limitStart = limitStart;
	}

	public Integer getLimitStart() {
		return limitStart;
	}

	public void setLimitEnd(Integer limitEnd) {
		this.limitEnd = limitEnd;
	}

	public Integer getLimitEnd() {
		return limitEnd;
	}

	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Long> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andTypeNameIsNull() {
			addCriterion("type_name is null");
			return (Criteria) this;
		}

		public Criteria andTypeNameIsNotNull() {
			addCriterion("type_name is not null");
			return (Criteria) this;
		}

		public Criteria andTypeNameEqualTo(String value) {
			addCriterion("type_name =", value, "typeName");
			return (Criteria) this;
		}

		public Criteria andTypeNameNotEqualTo(String value) {
			addCriterion("type_name <>", value, "typeName");
			return (Criteria) this;
		}

		public Criteria andTypeNameGreaterThan(String value) {
			addCriterion("type_name >", value, "typeName");
			return (Criteria) this;
		}

		public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
			addCriterion("type_name >=", value, "typeName");
			return (Criteria) this;
		}

		public Criteria andTypeNameLessThan(String value) {
			addCriterion("type_name <", value, "typeName");
			return (Criteria) this;
		}

		public Criteria andTypeNameLessThanOrEqualTo(String value) {
			addCriterion("type_name <=", value, "typeName");
			return (Criteria) this;
		}

		public Criteria andTypeNameLike(String value) {
			addCriterion("type_name like", value, "typeName");
			return (Criteria) this;
		}

		public Criteria andTypeNameNotLike(String value) {
			addCriterion("type_name not like", value, "typeName");
			return (Criteria) this;
		}

		public Criteria andTypeNameIn(List<String> values) {
			addCriterion("type_name in", values, "typeName");
			return (Criteria) this;
		}

		public Criteria andTypeNameNotIn(List<String> values) {
			addCriterion("type_name not in", values, "typeName");
			return (Criteria) this;
		}

		public Criteria andTypeNameBetween(String value1, String value2) {
			addCriterion("type_name between", value1, value2, "typeName");
			return (Criteria) this;
		}

		public Criteria andTypeNameNotBetween(String value1, String value2) {
			addCriterion("type_name not between", value1, value2, "typeName");
			return (Criteria) this;
		}

		public Criteria andTypeValueIsNull() {
			addCriterion("type_value is null");
			return (Criteria) this;
		}

		public Criteria andTypeValueIsNotNull() {
			addCriterion("type_value is not null");
			return (Criteria) this;
		}

		public Criteria andTypeValueEqualTo(String value) {
			addCriterion("type_value =", value, "typeValue");
			return (Criteria) this;
		}

		public Criteria andTypeValueNotEqualTo(String value) {
			addCriterion("type_value <>", value, "typeValue");
			return (Criteria) this;
		}

		public Criteria andTypeValueGreaterThan(String value) {
			addCriterion("type_value >", value, "typeValue");
			return (Criteria) this;
		}

		public Criteria andTypeValueGreaterThanOrEqualTo(String value) {
			addCriterion("type_value >=", value, "typeValue");
			return (Criteria) this;
		}

		public Criteria andTypeValueLessThan(String value) {
			addCriterion("type_value <", value, "typeValue");
			return (Criteria) this;
		}

		public Criteria andTypeValueLessThanOrEqualTo(String value) {
			addCriterion("type_value <=", value, "typeValue");
			return (Criteria) this;
		}

		public Criteria andTypeValueLike(String value) {
			addCriterion("type_value like", value, "typeValue");
			return (Criteria) this;
		}

		public Criteria andTypeValueNotLike(String value) {
			addCriterion("type_value not like", value, "typeValue");
			return (Criteria) this;
		}

		public Criteria andTypeValueIn(List<String> values) {
			addCriterion("type_value in", values, "typeValue");
			return (Criteria) this;
		}

		public Criteria andTypeValueNotIn(List<String> values) {
			addCriterion("type_value not in", values, "typeValue");
			return (Criteria) this;
		}

		public Criteria andTypeValueBetween(String value1, String value2) {
			addCriterion("type_value between", value1, value2, "typeValue");
			return (Criteria) this;
		}

		public Criteria andTypeValueNotBetween(String value1, String value2) {
			addCriterion("type_value not between", value1, value2, "typeValue");
			return (Criteria) this;
		}

		public Criteria andKeyIsNull() {
			addCriterion("key is null");
			return (Criteria) this;
		}

		public Criteria andKeyIsNotNull() {
			addCriterion("key is not null");
			return (Criteria) this;
		}

		public Criteria andKeyEqualTo(String value) {
			addCriterion("key =", value, "key");
			return (Criteria) this;
		}

		public Criteria andKeyNotEqualTo(String value) {
			addCriterion("key <>", value, "key");
			return (Criteria) this;
		}

		public Criteria andKeyGreaterThan(String value) {
			addCriterion("key >", value, "key");
			return (Criteria) this;
		}

		public Criteria andKeyGreaterThanOrEqualTo(String value) {
			addCriterion("key >=", value, "key");
			return (Criteria) this;
		}

		public Criteria andKeyLessThan(String value) {
			addCriterion("key <", value, "key");
			return (Criteria) this;
		}

		public Criteria andKeyLessThanOrEqualTo(String value) {
			addCriterion("key <=", value, "key");
			return (Criteria) this;
		}

		public Criteria andKeyLike(String value) {
			addCriterion("key like", value, "key");
			return (Criteria) this;
		}

		public Criteria andKeyNotLike(String value) {
			addCriterion("key not like", value, "key");
			return (Criteria) this;
		}

		public Criteria andKeyIn(List<String> values) {
			addCriterion("key in", values, "key");
			return (Criteria) this;
		}

		public Criteria andKeyNotIn(List<String> values) {
			addCriterion("key not in", values, "key");
			return (Criteria) this;
		}

		public Criteria andKeyBetween(String value1, String value2) {
			addCriterion("key between", value1, value2, "key");
			return (Criteria) this;
		}

		public Criteria andKeyNotBetween(String value1, String value2) {
			addCriterion("key not between", value1, value2, "key");
			return (Criteria) this;
		}

		public Criteria andValueIsNull() {
			addCriterion("value is null");
			return (Criteria) this;
		}

		public Criteria andValueIsNotNull() {
			addCriterion("value is not null");
			return (Criteria) this;
		}

		public Criteria andValueEqualTo(String value) {
			addCriterion("value =", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueNotEqualTo(String value) {
			addCriterion("value <>", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueGreaterThan(String value) {
			addCriterion("value >", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueGreaterThanOrEqualTo(String value) {
			addCriterion("value >=", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueLessThan(String value) {
			addCriterion("value <", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueLessThanOrEqualTo(String value) {
			addCriterion("value <=", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueLike(String value) {
			addCriterion("value like", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueNotLike(String value) {
			addCriterion("value not like", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueIn(List<String> values) {
			addCriterion("value in", values, "value");
			return (Criteria) this;
		}

		public Criteria andValueNotIn(List<String> values) {
			addCriterion("value not in", values, "value");
			return (Criteria) this;
		}

		public Criteria andValueBetween(String value1, String value2) {
			addCriterion("value between", value1, value2, "value");
			return (Criteria) this;
		}

		public Criteria andValueNotBetween(String value1, String value2) {
			addCriterion("value not between", value1, value2, "value");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNull() {
			addCriterion("description is null");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNotNull() {
			addCriterion("description is not null");
			return (Criteria) this;
		}

		public Criteria andDescriptionEqualTo(String value) {
			addCriterion("description =", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotEqualTo(String value) {
			addCriterion("description <>", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThan(String value) {
			addCriterion("description >", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("description >=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThan(String value) {
			addCriterion("description <", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThanOrEqualTo(String value) {
			addCriterion("description <=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLike(String value) {
			addCriterion("description like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotLike(String value) {
			addCriterion("description not like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionIn(List<String> values) {
			addCriterion("description in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotIn(List<String> values) {
			addCriterion("description not in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionBetween(String value1, String value2) {
			addCriterion("description between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotBetween(String value1, String value2) {
			addCriterion("description not between", value1, value2,
					"description");
			return (Criteria) this;
		}

		public Criteria andCreatedDateIsNull() {
			addCriterion("created_date is null");
			return (Criteria) this;
		}

		public Criteria andCreatedDateIsNotNull() {
			addCriterion("created_date is not null");
			return (Criteria) this;
		}

		public Criteria andCreatedDateEqualTo(Date value) {
			addCriterion("created_date =", value, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateNotEqualTo(Date value) {
			addCriterion("created_date <>", value, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateGreaterThan(Date value) {
			addCriterion("created_date >", value, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateGreaterThanOrEqualTo(Date value) {
			addCriterion("created_date >=", value, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateLessThan(Date value) {
			addCriterion("created_date <", value, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateLessThanOrEqualTo(Date value) {
			addCriterion("created_date <=", value, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateIn(List<Date> values) {
			addCriterion("created_date in", values, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateNotIn(List<Date> values) {
			addCriterion("created_date not in", values, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateBetween(Date value1, Date value2) {
			addCriterion("created_date between", value1, value2, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateNotBetween(Date value1, Date value2) {
			addCriterion("created_date not between", value1, value2,
					"createdDate");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}

	}

}