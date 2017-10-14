package com.taotao.testController;

import java.util.List;

import javax.print.attribute.standard.Severity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.pojo.ContentCategory;
import com.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Autowired
	ContentCategoryService service;
	/**
	 * 内容管理菜单
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ContentCategory>> questList(@RequestParam(value="id",defaultValue="0")Long id){
		try {
			ContentCategory contentCategory = new ContentCategory();
			contentCategory.setParentId(id);
			List<ContentCategory> queryListByWhere = service.queryListByWhere(contentCategory);
			return ResponseEntity.ok().body(queryListByWhere);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 增加内容管理选项
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseEntity<ContentCategory> addContentCategory(ContentCategory contentCategory){
			try {
				contentCategory.setIsParent(false);
				contentCategory.setStatus(1);
				contentCategory.setSortOrder(1);
				
				ContentCategory afterSave = service.saveSelective(contentCategory);
				//设置当前节点的父节点is_parent属性为true
				Long parentId = contentCategory.getParentId();
				if(parentId != null && parentId != 0){
					ContentCategory parentCon = new ContentCategory();
					parentCon.setId(parentId);
					parentCon.setIsParent(true);
					service.updateSelective(parentCon);
				}
				System.out.println(afterSave);
				return ResponseEntity.ok().body(afterSave);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	/**
	 * 更新
	 * @param contentCategory
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseEntity<String> editContentCategory(ContentCategory contentCategory){
		try {
			service.updateSelective(contentCategory);
			return ResponseEntity.ok().body("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ResponseEntity<String> delContentCategory(ContentCategory contentCategory){
		try {
			//此节点ID
			Long id = contentCategory.getId();
			//父id
			Long parentId = contentCategory.getParentId();
			//查找此节点是否有子节点,若存在子节点则删除剩余子节点
			service.delSonSelect(id);
			//删除项
			service.deleteById(id);
			//查找此节点的父项是否剩余子节点
			Integer queryByParentId = service.queryByParentId(parentId);
			//如果该节点不存在子节点,则父节点字段改为false
			if(queryByParentId == 0) {
				ContentCategory parent = new ContentCategory();
				parent.setId(parentId);
				parent.setIsParent(false);
				service.updateSelective(parent);
			}
			return ResponseEntity.ok().body("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}	
