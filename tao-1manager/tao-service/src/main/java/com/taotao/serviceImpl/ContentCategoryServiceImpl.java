package com.taotao.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.ContentCategoryMapper;
import com.taotao.pojo.ContentCategory;
import com.taotao.service.ContentCategoryService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ContentCategoryServiceImpl extends BaseServiceImpl<ContentCategory> implements ContentCategoryService {
	@Autowired
	ContentCategoryMapper mapper;
	/**
	 * 通过parentId查找残党
	 */
	@Override
	public Integer queryByParentId(Long parentId) {
		Example example = new Example(ContentCategory.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("parentId", parentId);
		return mapper.selectCountByExample(example);
	}
	/**
	 * 删除此ID节点下的所有子节点
	 */
	@Override
	public void delSonSelect(Long id) {
		Example example = new Example(ContentCategory.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("parentId", id);
		List<ContentCategory> selectByExample = mapper.selectByExample(example);
		//如果存在子节点
		if(selectByExample != null && selectByExample.size() > 0 ) {
			//递归
			for (ContentCategory contentCategory : selectByExample) {
				if(contentCategory.getIsParent()) {
					delSonSelect(contentCategory.getId());
				}else {
					mapper.deleteByPrimaryKey(contentCategory.getId());
				}
			}
		} 
		//删除此节点
		mapper.deleteByPrimaryKey(id);
	}
	
}
