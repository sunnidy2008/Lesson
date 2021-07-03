package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;
import java.math.BigDecimal;

/**
 * @Description  
 * @Author  yaosheng
 * @Date 2021-01-09 
 */

@Entity//标识这个是一个与数据库表对应的entity类
@Data//lombok神器的一个注解，后面专题介绍
@Table ( name ="user" )//这个类是与数据库的哪个表对应的
public class User implements Serializable {

	private static final long serialVersionUID =  829933141479418804L;

	/**
	 * 主键ID
	 */
	@Id//这个字段是数据库表的主键
   	@Column(name = "id" )//这个属性对应表的哪个字段
	@GeneratedValue(strategy= GenerationType.IDENTITY)//主键采用数据库自增方式
	private Long id;

	/**
	 * 部门id
	 */
   	@Column(name = "dpt_id" )
	private Long dptId;

	/**
	 * 姓名
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 年龄
	 */
   	@Column(name = "age" )
	private Long age;

	/**
	 * 邮箱
	 */
   	@Column(name = "email" )
	private String email;

	/**
	 * 头像
	 */
   	@Column(name = "head_img" )
	private String headImg;

}
