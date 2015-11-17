package com.cnlot.booking.domain;

import java.io.Serializable;

public class Trainer implements Serializable {
	/**  */
	private Long id;
	/** 真实姓名 */
	private String name;
	/**  */
	private Integer sex;
	/** 年龄 */
	private Integer age;
	/** 主要运动类型 */
	private Integer gameType;
	/** 0 未验证 1 验证通过 2 验证不通过 */
	private Integer status;
	/** 资格标签 */
	private String tags;
	/**  */
	private Integer cityCode;
	/**  */
	private Integer areaCode;
	/** 教龄 */
	private Integer teachingAge;
	/** 教学场馆Id */
	private Long venueId;
	/** 教学场馆 */
	private String venueName;
	/** 平均五星评分 */
	private java.math.BigDecimal star;
	/** 成功预约次数 */
	private Long bookingTimes;
	/** 介绍 */
	private String introduction;

	/**  */
	public Long getId() {
		return id;
	}
	/**  */
	public void setId(Long id) {
		this.id = id;
	}

	/** 真实姓名 */
	public String getName() {
		return name;
	}
	/** 真实姓名 */
	public void setName(String name) {
		this.name = name;
	}

	/**  */
	public Integer getSex() {
		return sex;
	}
	/**  */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/** 年龄 */
	public Integer getAge() {
		return age;
	}
	/** 年龄 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/** 主要运动类型 */
	public Integer getGameType() {
		return gameType;
	}
	/** 主要运动类型 */
	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}

	/** 0 未验证 1 验证通过 2 验证不通过 */
	public Integer getStatus() {
		return status;
	}
	/** 0 未验证 1 验证通过 2 验证不通过 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 资格标签 */
	public String getTags() {
		return tags;
	}
	/** 资格标签 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**  */
	public Integer getCityCode() {
		return cityCode;
	}
	/**  */
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	/**  */
	public Integer getAreaCode() {
		return areaCode;
	}
	/**  */
	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	/** 教龄 */
	public Integer getTeachingAge() {
		return teachingAge;
	}
	/** 教龄 */
	public void setTeachingAge(Integer teachingAge) {
		this.teachingAge = teachingAge;
	}

	/** 教学场馆Id */
	public Long getVenueId() {
		return venueId;
	}
	/** 教学场馆Id */
	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}

	/** 教学场馆 */
	public String getVenueName() {
		return venueName;
	}
	/** 教学场馆 */
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	/** 平均五星评分 */
	public java.math.BigDecimal getStar() {
		return star;
	}
	/** 平均五星评分 */
	public void setStar(java.math.BigDecimal star) {
		this.star = star;
	}

	/** 成功预约次数 */
	public Long getBookingTimes() {
		return bookingTimes;
	}
	/** 成功预约次数 */
	public void setBookingTimes(Long bookingTimes) {
		this.bookingTimes = bookingTimes;
	}

	/** 介绍 */
	public String getIntroduction() {
		return introduction;
	}
	/** 介绍 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
