package com.foriba.jws1.base;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


/**
 * 	@author S. Gökhan Topçu
 * 
 * 	Bütün Entity'lerin extend edileceği baz Entity - versiyon ve son değiştirilme tarihini içerir
 * 	trimAllStringFields() metodu insert ve update esnasında çağrılır ve entity'deki tüm string alanların
 * 	önce trim, sonra uzunluk kontrolüyle substring yapılmasını sağlar.
 * 
 *	Yeni entity yaratırken:
 *	- BaseEntity extend edilmeli - GenericDAO ile versiyon ve son değiştirilme tarihi kaydını tutar
 *	- SYS_VERSION ve SYS_LAST_UPDATE alanları ve getter/setter metodları silinmeli
 *	- Saat bilgisiyle tutulması gereken DATE alanlarının Temporal tipi timestamp yapılmalı
 *  - BigDecimal olarak yaratılan integer tipler, null kayıt atılmaması için dönüştürülmeli
 *	- Sequence allocation size'lar entity'lerde 1 olarak set edilmeli
 *	- Sequence kullanan IN ve OUT tablolarının sequence'ları ayrılmalı
 *	- String alanların uzunlukları, tablo alan uzunluklarına göre @Column(name="NAME", length=12) şeklinde
 *	  otomatik substring yapılması için belirtilmeli
 */
public abstract class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -6349452865348636014L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SYS_LAST_UPDATE")
	private Date sysLastUpdate;

	@Version
	@Column(name="SYS_VERSION")
	private long sysVersion;
	
	@PrePersist
	@PreUpdate
	public void trimAllStringFields() throws Exception {
		trimStringFields(getClass());
	}

	private void trimStringFields(Class<?> entityClass) throws Exception {
		if(entityClass != null) {
			for(Field field : entityClass.getDeclaredFields()) {
				if(field.getType().equals(String.class)) {
					String fieldName = field.getName().substring(0, 1).toUpperCase(Locale.ENGLISH) + field.getName().substring(1);
					Method getterMethod = entityClass.getDeclaredMethod("get" + fieldName);
					String s = (String)getterMethod.invoke(this);
					if(s != null) {
						s = s.trim();
						Column c = field.getAnnotation(Column.class);
						if(c != null && c.length() > 0 && s.length() > c.length()) {
							s = s.substring(0, c.length());
						}
						Method setterMethod = entityClass.getDeclaredMethod("set" + fieldName, String.class);
						setterMethod.invoke(this, s);
					}
				}
			}
			trimStringFields(entityClass.getSuperclass());
		}
	}
	
	public Date getSysLastUpdate() {
		return this.sysLastUpdate;
	}

	public void setSysLastUpdate(Date sysLastUpdate) {
		this.sysLastUpdate = sysLastUpdate;
	}

	public long getSysVersion() {
		return sysVersion;
	}

	public void setSysVersion(long sysVersion) {
		this.sysVersion = sysVersion;
	}


	
}
