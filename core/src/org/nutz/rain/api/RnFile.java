package org.nutz.rain.api;

import java.util.Date;
import java.util.Map;

/**
 * 封装了一个文件对象的所有索引信息
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public interface RnFile {

	/**
	 * @return 文件唯一的标识符（字符串形式）
	 */
	String getId();

	/**
	 * @return 文件全名
	 */
	String getName();

	/**
	 * @return 文件类型（肯定为小写形式）
	 */
	String getType();

	/**
	 * @return 文件全路径
	 */
	String getPath();

	/**
	 * @return 自己的父目录路径，如果是主目录，则返回 null
	 */
	String getParentPath();

	/**
	 * @return 被链接的文件路径
	 */
	String getLinkPath();

	/**
	 * @return 当前对象是否是一个软链接
	 */
	boolean isLink();

	/**
	 * @return 当前文件是否是隐藏文件
	 */
	boolean isHidden();

	/**
	 * 设置自身的隐藏属性
	 * 
	 * @param hidden
	 *            是否隐藏
	 * 
	 * @return 自身
	 */
	RnFile setHidden(boolean hidden);

	/**
	 * @return 文件的扩展属性表
	 */
	Map<String, Object> getAttributeMap();

	/**
	 * 获取属性值
	 * 
	 * @param name
	 *            属性名
	 * @return null 表示属性不存在
	 */
	Object getAttr(String name);

	/**
	 * @param name
	 *            属性名
	 * @return -1 表示属性不存在
	 */
	int getAttrAsInt(String name);

	/**
	 * @param name
	 *            属性名
	 * @return 如果属性不存在 返回 false
	 */
	boolean getAttrAsBoolean(String name);

	/**
	 * @param name
	 *            属性名
	 * @return -1 表示属性不存在
	 */
	long getAttrAsLong(String name);

	/**
	 * @param name
	 *            属性名
	 * @return null 表示属性不存在
	 */
	Date getAttrAsDate(String name);

	/**
	 * @param name
	 *            属性名
	 * @return null 表示属性不存在
	 */
	String getAttrAsString(String name);

	/**
	 * @param name
	 *            属性名
	 * @return 0.0 表示属性不存在
	 */
	float getAttrAsFloat(String name);

	/**
	 * @param name
	 *            属性名
	 * @return 属性是否存在
	 */
	boolean hasAttr(String name);

	/**
	 * 设置属性的值
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性的值
	 */
	void setAttr(String name, Object value);

	/**
	 * @param f
	 *            相对的文件
	 * @return 本文件，相对于另外一个文件的路径。 如果两个文件路径相同，返回 "."
	 */
	String getRelativePath(RnFile f);

}
