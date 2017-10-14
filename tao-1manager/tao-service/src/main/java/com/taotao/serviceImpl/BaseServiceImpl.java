package com.taotao.serviceImpl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.taotao.pojo.BasePojo;
import com.taotao.service.BaserService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public class BaseServiceImpl<T extends BasePojo> implements BaserService<T> {
	
	Class<T>clazz;
	
	public BaseServiceImpl() {
	try {
		Type type = this.getClass().getGenericSuperclass(); //取得 Class
		ParameterizedType ptype = (ParameterizedType)type; //转成参数化类型
		clazz = (Class<T>) ptype.getActualTypeArguments()[0];
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	@Autowired
	private Mapper<T> mapper;
	
	@Override
	public T queryById(Serializable id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<T> queryAll() {
		return mapper.selectAll();
	}

	@Override
	public Integer queryCountByWhere(T t) {
		return mapper.selectCount(t);
	}

	@Override
	public List<T> queryListByWhere(T t) {
		return mapper.select(t);
	}

	@Override
	public List<T> queryListByPage(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		return mapper.selectAll();
	}

	@Override
	public T saveSelective(T t) {
		if(t.getCreated() == null) {
			t.setCreated(new Date());
		}
		if(t.getUpdated() == null) {
			t.setUpdated(t.getCreated());
		}
		mapper.insertSelective(t);
		return t;
	}

	@Override
	public void updateSelective(T t) {
		if(t.getUpdated() == null) {
			t.setUpdated(new Date());
		}
		mapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public void deleteById(Serializable id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteByIds(Serializable[] ids) {
		Example example = new Example(clazz);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIn("id", Arrays.asList(ids));
		mapper.deleteByExample(example);
	}


}
