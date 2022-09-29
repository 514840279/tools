package org.chuxue.application.common.utils.constant;

/**
 * 文件名 ： DatabasePlantEnum.java
 * 包 名 ： org.chuxue.application.common.utils.constant
 * 描 述 ： 数据库平台枚举
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年9月29日 下午2:14:53
 * 版 本 ： V1.0
 */
public enum DatabasePlantEnum {

	MYSQL("MYSQL"), ORACLE("ORACLE"), H2("H2");

	private String plant;

	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param string
	 * 作 者 ： Administrator
	 * @throws
	 */
	DatabasePlantEnum(String name) {
		this.plant = name;
	}

	/**
	 * 方法名 ： getPlant
	 * 功 能 ： 返回变量 plant 的值
	 *
	 * @return: String
	 */
	public String getPlant() {
		return plant;
	}

	/**
	 * 方法名 ： setPlant
	 * 功 能 ： 设置变量 plant 的值
	 */
	public void setPlant(String plant) {
		this.plant = plant;
	}

	public static DatabasePlantEnum value(String name) {
		for (DatabasePlantEnum dpe : values()) {
			if (dpe.getPlant().equals(name)) {
				return dpe;
			}
		}
		return null;
		
	}

}
