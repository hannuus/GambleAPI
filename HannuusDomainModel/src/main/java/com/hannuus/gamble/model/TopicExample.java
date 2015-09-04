package com.hannuus.gamble.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TopicExample() {
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
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
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

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
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

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Long value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Long value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Long value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Long value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Long> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Long> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Long value1, Long value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andSpecialIdIsNull() {
            addCriterion("special_id is null");
            return (Criteria) this;
        }

        public Criteria andSpecialIdIsNotNull() {
            addCriterion("special_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialIdEqualTo(Long value) {
            addCriterion("special_id =", value, "specialId");
            return (Criteria) this;
        }

        public Criteria andSpecialIdNotEqualTo(Long value) {
            addCriterion("special_id <>", value, "specialId");
            return (Criteria) this;
        }

        public Criteria andSpecialIdGreaterThan(Long value) {
            addCriterion("special_id >", value, "specialId");
            return (Criteria) this;
        }

        public Criteria andSpecialIdGreaterThanOrEqualTo(Long value) {
            addCriterion("special_id >=", value, "specialId");
            return (Criteria) this;
        }

        public Criteria andSpecialIdLessThan(Long value) {
            addCriterion("special_id <", value, "specialId");
            return (Criteria) this;
        }

        public Criteria andSpecialIdLessThanOrEqualTo(Long value) {
            addCriterion("special_id <=", value, "specialId");
            return (Criteria) this;
        }

        public Criteria andSpecialIdIn(List<Long> values) {
            addCriterion("special_id in", values, "specialId");
            return (Criteria) this;
        }

        public Criteria andSpecialIdNotIn(List<Long> values) {
            addCriterion("special_id not in", values, "specialId");
            return (Criteria) this;
        }

        public Criteria andSpecialIdBetween(Long value1, Long value2) {
            addCriterion("special_id between", value1, value2, "specialId");
            return (Criteria) this;
        }

        public Criteria andSpecialIdNotBetween(Long value1, Long value2) {
            addCriterion("special_id not between", value1, value2, "specialId");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNull() {
            addCriterion("enabled is null");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNotNull() {
            addCriterion("enabled is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledEqualTo(Integer value) {
            addCriterion("enabled =", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotEqualTo(Integer value) {
            addCriterion("enabled <>", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThan(Integer value) {
            addCriterion("enabled >", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThanOrEqualTo(Integer value) {
            addCriterion("enabled >=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThan(Integer value) {
            addCriterion("enabled <", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThanOrEqualTo(Integer value) {
            addCriterion("enabled <=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledIn(List<Integer> values) {
            addCriterion("enabled in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotIn(List<Integer> values) {
            addCriterion("enabled not in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledBetween(Integer value1, Integer value2) {
            addCriterion("enabled between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotBetween(Integer value1, Integer value2) {
            addCriterion("enabled not between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andHitsIsNull() {
            addCriterion("hits is null");
            return (Criteria) this;
        }

        public Criteria andHitsIsNotNull() {
            addCriterion("hits is not null");
            return (Criteria) this;
        }

        public Criteria andHitsEqualTo(Long value) {
            addCriterion("hits =", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotEqualTo(Long value) {
            addCriterion("hits <>", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsGreaterThan(Long value) {
            addCriterion("hits >", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsGreaterThanOrEqualTo(Long value) {
            addCriterion("hits >=", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsLessThan(Long value) {
            addCriterion("hits <", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsLessThanOrEqualTo(Long value) {
            addCriterion("hits <=", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsIn(List<Long> values) {
            addCriterion("hits in", values, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotIn(List<Long> values) {
            addCriterion("hits not in", values, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsBetween(Long value1, Long value2) {
            addCriterion("hits between", value1, value2, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotBetween(Long value1, Long value2) {
            addCriterion("hits not between", value1, value2, "hits");
            return (Criteria) this;
        }

        public Criteria andReplyCountIsNull() {
            addCriterion("reply_count is null");
            return (Criteria) this;
        }

        public Criteria andReplyCountIsNotNull() {
            addCriterion("reply_count is not null");
            return (Criteria) this;
        }

        public Criteria andReplyCountEqualTo(Long value) {
            addCriterion("reply_count =", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountNotEqualTo(Long value) {
            addCriterion("reply_count <>", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountGreaterThan(Long value) {
            addCriterion("reply_count >", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountGreaterThanOrEqualTo(Long value) {
            addCriterion("reply_count >=", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountLessThan(Long value) {
            addCriterion("reply_count <", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountLessThanOrEqualTo(Long value) {
            addCriterion("reply_count <=", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountIn(List<Long> values) {
            addCriterion("reply_count in", values, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountNotIn(List<Long> values) {
            addCriterion("reply_count not in", values, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountBetween(Long value1, Long value2) {
            addCriterion("reply_count between", value1, value2, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountNotBetween(Long value1, Long value2) {
            addCriterion("reply_count not between", value1, value2, "replyCount");
            return (Criteria) this;
        }

        public Criteria andModifiedByIsNull() {
            addCriterion("modified_by is null");
            return (Criteria) this;
        }

        public Criteria andModifiedByIsNotNull() {
            addCriterion("modified_by is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedByEqualTo(Long value) {
            addCriterion("modified_by =", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByNotEqualTo(Long value) {
            addCriterion("modified_by <>", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByGreaterThan(Long value) {
            addCriterion("modified_by >", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByGreaterThanOrEqualTo(Long value) {
            addCriterion("modified_by >=", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByLessThan(Long value) {
            addCriterion("modified_by <", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByLessThanOrEqualTo(Long value) {
            addCriterion("modified_by <=", value, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByIn(List<Long> values) {
            addCriterion("modified_by in", values, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByNotIn(List<Long> values) {
            addCriterion("modified_by not in", values, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByBetween(Long value1, Long value2) {
            addCriterion("modified_by between", value1, value2, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedByNotBetween(Long value1, Long value2) {
            addCriterion("modified_by not between", value1, value2, "modifiedBy");
            return (Criteria) this;
        }

        public Criteria andModifiedDateIsNull() {
            addCriterion("modified_date is null");
            return (Criteria) this;
        }

        public Criteria andModifiedDateIsNotNull() {
            addCriterion("modified_date is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedDateEqualTo(Date value) {
            addCriterion("modified_date =", value, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateNotEqualTo(Date value) {
            addCriterion("modified_date <>", value, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateGreaterThan(Date value) {
            addCriterion("modified_date >", value, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("modified_date >=", value, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateLessThan(Date value) {
            addCriterion("modified_date <", value, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateLessThanOrEqualTo(Date value) {
            addCriterion("modified_date <=", value, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateIn(List<Date> values) {
            addCriterion("modified_date in", values, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateNotIn(List<Date> values) {
            addCriterion("modified_date not in", values, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateBetween(Date value1, Date value2) {
            addCriterion("modified_date between", value1, value2, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andModifiedDateNotBetween(Date value1, Date value2) {
            addCriterion("modified_date not between", value1, value2, "modifiedDate");
            return (Criteria) this;
        }

        public Criteria andRepliedByIsNull() {
            addCriterion("replied_by is null");
            return (Criteria) this;
        }

        public Criteria andRepliedByIsNotNull() {
            addCriterion("replied_by is not null");
            return (Criteria) this;
        }

        public Criteria andRepliedByEqualTo(Long value) {
            addCriterion("replied_by =", value, "repliedBy");
            return (Criteria) this;
        }

        public Criteria andRepliedByNotEqualTo(Long value) {
            addCriterion("replied_by <>", value, "repliedBy");
            return (Criteria) this;
        }

        public Criteria andRepliedByGreaterThan(Long value) {
            addCriterion("replied_by >", value, "repliedBy");
            return (Criteria) this;
        }

        public Criteria andRepliedByGreaterThanOrEqualTo(Long value) {
            addCriterion("replied_by >=", value, "repliedBy");
            return (Criteria) this;
        }

        public Criteria andRepliedByLessThan(Long value) {
            addCriterion("replied_by <", value, "repliedBy");
            return (Criteria) this;
        }

        public Criteria andRepliedByLessThanOrEqualTo(Long value) {
            addCriterion("replied_by <=", value, "repliedBy");
            return (Criteria) this;
        }

        public Criteria andRepliedByIn(List<Long> values) {
            addCriterion("replied_by in", values, "repliedBy");
            return (Criteria) this;
        }

        public Criteria andRepliedByNotIn(List<Long> values) {
            addCriterion("replied_by not in", values, "repliedBy");
            return (Criteria) this;
        }

        public Criteria andRepliedByBetween(Long value1, Long value2) {
            addCriterion("replied_by between", value1, value2, "repliedBy");
            return (Criteria) this;
        }

        public Criteria andRepliedByNotBetween(Long value1, Long value2) {
            addCriterion("replied_by not between", value1, value2, "repliedBy");
            return (Criteria) this;
        }

        public Criteria andRepliedDateIsNull() {
            addCriterion("replied_date is null");
            return (Criteria) this;
        }

        public Criteria andRepliedDateIsNotNull() {
            addCriterion("replied_date is not null");
            return (Criteria) this;
        }

        public Criteria andRepliedDateEqualTo(Date value) {
            addCriterion("replied_date =", value, "repliedDate");
            return (Criteria) this;
        }

        public Criteria andRepliedDateNotEqualTo(Date value) {
            addCriterion("replied_date <>", value, "repliedDate");
            return (Criteria) this;
        }

        public Criteria andRepliedDateGreaterThan(Date value) {
            addCriterion("replied_date >", value, "repliedDate");
            return (Criteria) this;
        }

        public Criteria andRepliedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("replied_date >=", value, "repliedDate");
            return (Criteria) this;
        }

        public Criteria andRepliedDateLessThan(Date value) {
            addCriterion("replied_date <", value, "repliedDate");
            return (Criteria) this;
        }

        public Criteria andRepliedDateLessThanOrEqualTo(Date value) {
            addCriterion("replied_date <=", value, "repliedDate");
            return (Criteria) this;
        }

        public Criteria andRepliedDateIn(List<Date> values) {
            addCriterion("replied_date in", values, "repliedDate");
            return (Criteria) this;
        }

        public Criteria andRepliedDateNotIn(List<Date> values) {
            addCriterion("replied_date not in", values, "repliedDate");
            return (Criteria) this;
        }

        public Criteria andRepliedDateBetween(Date value1, Date value2) {
            addCriterion("replied_date between", value1, value2, "repliedDate");
            return (Criteria) this;
        }

        public Criteria andRepliedDateNotBetween(Date value1, Date value2) {
            addCriterion("replied_date not between", value1, value2, "repliedDate");
            return (Criteria) this;
        }

        public Criteria andIconIdIsNull() {
            addCriterion("icon_id is null");
            return (Criteria) this;
        }

        public Criteria andIconIdIsNotNull() {
            addCriterion("icon_id is not null");
            return (Criteria) this;
        }

        public Criteria andIconIdEqualTo(Long value) {
            addCriterion("icon_id =", value, "iconId");
            return (Criteria) this;
        }

        public Criteria andIconIdNotEqualTo(Long value) {
            addCriterion("icon_id <>", value, "iconId");
            return (Criteria) this;
        }

        public Criteria andIconIdGreaterThan(Long value) {
            addCriterion("icon_id >", value, "iconId");
            return (Criteria) this;
        }

        public Criteria andIconIdGreaterThanOrEqualTo(Long value) {
            addCriterion("icon_id >=", value, "iconId");
            return (Criteria) this;
        }

        public Criteria andIconIdLessThan(Long value) {
            addCriterion("icon_id <", value, "iconId");
            return (Criteria) this;
        }

        public Criteria andIconIdLessThanOrEqualTo(Long value) {
            addCriterion("icon_id <=", value, "iconId");
            return (Criteria) this;
        }

        public Criteria andIconIdIn(List<Long> values) {
            addCriterion("icon_id in", values, "iconId");
            return (Criteria) this;
        }

        public Criteria andIconIdNotIn(List<Long> values) {
            addCriterion("icon_id not in", values, "iconId");
            return (Criteria) this;
        }

        public Criteria andIconIdBetween(Long value1, Long value2) {
            addCriterion("icon_id between", value1, value2, "iconId");
            return (Criteria) this;
        }

        public Criteria andIconIdNotBetween(Long value1, Long value2) {
            addCriterion("icon_id not between", value1, value2, "iconId");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("\"state\" is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("\"state\" is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("\"state\" =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("\"state\" <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("\"state\" >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"state\" >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("\"state\" <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("\"state\" <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("\"state\" in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("\"state\" not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("\"state\" between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("\"state\" not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNull() {
            addCriterion("image_url is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("image_url is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("image_url =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("image_url <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("image_url >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("image_url >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("image_url <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("image_url <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("image_url like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("image_url not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("image_url in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("image_url not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("image_url between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("image_url not between", value1, value2, "imageUrl");
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
            addCriterion("created_date not between", value1, value2, "createdDate");
            return (Criteria) this;
        }

        public Criteria andFollowCountIsNull() {
            addCriterion("follow_count is null");
            return (Criteria) this;
        }

        public Criteria andFollowCountIsNotNull() {
            addCriterion("follow_count is not null");
            return (Criteria) this;
        }

        public Criteria andFollowCountEqualTo(Long value) {
            addCriterion("follow_count =", value, "followCount");
            return (Criteria) this;
        }

        public Criteria andFollowCountNotEqualTo(Long value) {
            addCriterion("follow_count <>", value, "followCount");
            return (Criteria) this;
        }

        public Criteria andFollowCountGreaterThan(Long value) {
            addCriterion("follow_count >", value, "followCount");
            return (Criteria) this;
        }

        public Criteria andFollowCountGreaterThanOrEqualTo(Long value) {
            addCriterion("follow_count >=", value, "followCount");
            return (Criteria) this;
        }

        public Criteria andFollowCountLessThan(Long value) {
            addCriterion("follow_count <", value, "followCount");
            return (Criteria) this;
        }

        public Criteria andFollowCountLessThanOrEqualTo(Long value) {
            addCriterion("follow_count <=", value, "followCount");
            return (Criteria) this;
        }

        public Criteria andFollowCountIn(List<Long> values) {
            addCriterion("follow_count in", values, "followCount");
            return (Criteria) this;
        }

        public Criteria andFollowCountNotIn(List<Long> values) {
            addCriterion("follow_count not in", values, "followCount");
            return (Criteria) this;
        }

        public Criteria andFollowCountBetween(Long value1, Long value2) {
            addCriterion("follow_count between", value1, value2, "followCount");
            return (Criteria) this;
        }

        public Criteria andFollowCountNotBetween(Long value1, Long value2) {
            addCriterion("follow_count not between", value1, value2, "followCount");
            return (Criteria) this;
        }

        public Criteria andTagIdIsNull() {
            addCriterion("tag_id is null");
            return (Criteria) this;
        }

        public Criteria andTagIdIsNotNull() {
            addCriterion("tag_id is not null");
            return (Criteria) this;
        }

        public Criteria andTagIdEqualTo(Long value) {
            addCriterion("tag_id =", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdNotEqualTo(Long value) {
            addCriterion("tag_id <>", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdGreaterThan(Long value) {
            addCriterion("tag_id >", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tag_id >=", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdLessThan(Long value) {
            addCriterion("tag_id <", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdLessThanOrEqualTo(Long value) {
            addCriterion("tag_id <=", value, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdIn(List<Long> values) {
            addCriterion("tag_id in", values, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdNotIn(List<Long> values) {
            addCriterion("tag_id not in", values, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdBetween(Long value1, Long value2) {
            addCriterion("tag_id between", value1, value2, "tagId");
            return (Criteria) this;
        }

        public Criteria andTagIdNotBetween(Long value1, Long value2) {
            addCriterion("tag_id not between", value1, value2, "tagId");
            return (Criteria) this;
        }

        public Criteria andUpCountIsNull() {
            addCriterion("up_count is null");
            return (Criteria) this;
        }

        public Criteria andUpCountIsNotNull() {
            addCriterion("up_count is not null");
            return (Criteria) this;
        }

        public Criteria andUpCountEqualTo(Long value) {
            addCriterion("up_count =", value, "upCount");
            return (Criteria) this;
        }

        public Criteria andUpCountNotEqualTo(Long value) {
            addCriterion("up_count <>", value, "upCount");
            return (Criteria) this;
        }

        public Criteria andUpCountGreaterThan(Long value) {
            addCriterion("up_count >", value, "upCount");
            return (Criteria) this;
        }

        public Criteria andUpCountGreaterThanOrEqualTo(Long value) {
            addCriterion("up_count >=", value, "upCount");
            return (Criteria) this;
        }

        public Criteria andUpCountLessThan(Long value) {
            addCriterion("up_count <", value, "upCount");
            return (Criteria) this;
        }

        public Criteria andUpCountLessThanOrEqualTo(Long value) {
            addCriterion("up_count <=", value, "upCount");
            return (Criteria) this;
        }

        public Criteria andUpCountIn(List<Long> values) {
            addCriterion("up_count in", values, "upCount");
            return (Criteria) this;
        }

        public Criteria andUpCountNotIn(List<Long> values) {
            addCriterion("up_count not in", values, "upCount");
            return (Criteria) this;
        }

        public Criteria andUpCountBetween(Long value1, Long value2) {
            addCriterion("up_count between", value1, value2, "upCount");
            return (Criteria) this;
        }

        public Criteria andUpCountNotBetween(Long value1, Long value2) {
            addCriterion("up_count not between", value1, value2, "upCount");
            return (Criteria) this;
        }

        public Criteria andDownCountIsNull() {
            addCriterion("down_count is null");
            return (Criteria) this;
        }

        public Criteria andDownCountIsNotNull() {
            addCriterion("down_count is not null");
            return (Criteria) this;
        }

        public Criteria andDownCountEqualTo(Long value) {
            addCriterion("down_count =", value, "downCount");
            return (Criteria) this;
        }

        public Criteria andDownCountNotEqualTo(Long value) {
            addCriterion("down_count <>", value, "downCount");
            return (Criteria) this;
        }

        public Criteria andDownCountGreaterThan(Long value) {
            addCriterion("down_count >", value, "downCount");
            return (Criteria) this;
        }

        public Criteria andDownCountGreaterThanOrEqualTo(Long value) {
            addCriterion("down_count >=", value, "downCount");
            return (Criteria) this;
        }

        public Criteria andDownCountLessThan(Long value) {
            addCriterion("down_count <", value, "downCount");
            return (Criteria) this;
        }

        public Criteria andDownCountLessThanOrEqualTo(Long value) {
            addCriterion("down_count <=", value, "downCount");
            return (Criteria) this;
        }

        public Criteria andDownCountIn(List<Long> values) {
            addCriterion("down_count in", values, "downCount");
            return (Criteria) this;
        }

        public Criteria andDownCountNotIn(List<Long> values) {
            addCriterion("down_count not in", values, "downCount");
            return (Criteria) this;
        }

        public Criteria andDownCountBetween(Long value1, Long value2) {
            addCriterion("down_count between", value1, value2, "downCount");
            return (Criteria) this;
        }

        public Criteria andDownCountNotBetween(Long value1, Long value2) {
            addCriterion("down_count not between", value1, value2, "downCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountIsNull() {
            addCriterion("collection_count is null");
            return (Criteria) this;
        }

        public Criteria andCollectionCountIsNotNull() {
            addCriterion("collection_count is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionCountEqualTo(Long value) {
            addCriterion("collection_count =", value, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountNotEqualTo(Long value) {
            addCriterion("collection_count <>", value, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountGreaterThan(Long value) {
            addCriterion("collection_count >", value, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountGreaterThanOrEqualTo(Long value) {
            addCriterion("collection_count >=", value, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountLessThan(Long value) {
            addCriterion("collection_count <", value, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountLessThanOrEqualTo(Long value) {
            addCriterion("collection_count <=", value, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountIn(List<Long> values) {
            addCriterion("collection_count in", values, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountNotIn(List<Long> values) {
            addCriterion("collection_count not in", values, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountBetween(Long value1, Long value2) {
            addCriterion("collection_count between", value1, value2, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountNotBetween(Long value1, Long value2) {
            addCriterion("collection_count not between", value1, value2, "collectionCount");
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

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
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