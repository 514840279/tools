package org.chuxue.application.common.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 文件名 ： MybatisBaseServiceImpl.java
 * 包 名 ： org.chuxue.application.common.base
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年7月5日 上午9:48:53
 * 版 本 ： V1.0
 */
public class MybatisBaseServiceImpl<T> extends ServiceImpl<MybatisBaseDao<T>, T> implements IService<T> {

}
