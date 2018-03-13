package com.foriba.jws1.service;

import java.util.List;

import com.foriba.jws1.base.BaseEntity;
import com.foriba.jws1.entity.Jws1Order;

public interface SearchOrder<T extends BaseEntity>{

	public List<Jws1Order> searchOrderProductName(String ProductName) throws Exception;
}
