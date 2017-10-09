package com.taotao.mapper;

import com.taotao.pojo.Item;
import com.taotao.pojo.ItemExample;
import java.util.List;

public interface ItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbggenerated Sun Oct 08 19:43:58 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbggenerated Sun Oct 08 19:43:58 CST 2017
     */
    int insert(Item record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbggenerated Sun Oct 08 19:43:58 CST 2017
     */
    int insertSelective(Item record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbggenerated Sun Oct 08 19:43:58 CST 2017
     */
    List<Item> selectByExample(ItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbggenerated Sun Oct 08 19:43:58 CST 2017
     */
    Item selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbggenerated Sun Oct 08 19:43:58 CST 2017
     */
    int updateByPrimaryKeySelective(Item record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbggenerated Sun Oct 08 19:43:58 CST 2017
     */
    int updateByPrimaryKey(Item record);
}