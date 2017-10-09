package com.taotao.serviceImpl;

import java.io.Serializable;
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
	public void saveSelective(T t) {
		if(t.getCreateTime() == null) {
			t.setCreateTime(new Date());
		}
		if(t.getUpdateTime() == null) {
			t.setUpdateTime(t.getCreateTime());
		}
		mapper.insertSelective(t);
		
	}

	@Override
	public void updateSelective(T t) {
		if(t.getUpdateTime() == null) {
			t.setUpdateTime(new Date());
		}
		mapper.insertSelective(t);
	}

	@Override
	public void deleteById(Serializable id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteByIds(Serializable[] ids) {
		Example example = new Example(this.getClass());
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIn("id", Arrays.asList(ids));
		mapper.deleteByExample(example);
	}


}
