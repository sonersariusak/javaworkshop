package com.foriba.jws1.web.model;

import java.io.Serializable;

/**
 * @author murat.demir
 * 
 */
public class File implements Serializable {

	private static final long serialVersionUID = 2376356927567393387L;

	private String pName;
	private String mime;
	private long length;
	private byte[] data;

	public File() {}

	public File(String pName, String mime, long length, byte[] data) {
		this.pName = pName;
		this.mime = mime;
		this.length = length;
		this.data = data;
	}


	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpName() {
		return pName;
	}

}
