package com.taotao.mapper;

import com.taotao.pojo.ItemDesc;
import com.taotao.pojo.ItemDescExample;
import java.util.List;

public interface ItemDescMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbggenerated Sun Oct 08 19:48:04 CST 2017
     */
    int insert(ItemDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbggenerated Sun Oct 08 19:48:04 CST 2017
     */
    int insertSelective(ItemDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbggenerated Sun Oct 08 19:48:04 CST 2017
     */
    List<ItemDesc> selectByExampleWithBLOBs(ItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbggenerated Sun Oct 08 19:48:04 CST 2017
     */
    List<ItemDesc> selectByExample(ItemDescExample example);
}