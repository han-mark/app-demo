package com.bird.business.domain;

import java.io.Serializable;

public class SysUser implements Serializable {

    private String uuid;
    private String uname;
    private String sex;
	private String age;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "SysUser{" +
				"uuid='" + uuid + '\'' +
				", uname='" + uname + '\'' +
				", sex='" + sex + '\'' +
				", age='" + age + '\'' +
				'}';
	}
}