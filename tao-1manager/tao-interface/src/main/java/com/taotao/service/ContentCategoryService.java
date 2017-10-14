package com.taotao.service;

import com.taotao.pojo.ContentCategory;

public interface ContentCategoryService extends BaserService<ContentCategory>{
	Integer queryByParentId(Long parentId);

	void delSonSelect(Long id);
}
