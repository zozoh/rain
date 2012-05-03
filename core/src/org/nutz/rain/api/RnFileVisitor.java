package org.nutz.rain.api;

/**
 * 一个文件访问的迭代器
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public interface RnFileVisitor {

	/**
	 * 执行迭代
	 * 
	 * @param index
	 *            之前访问过多少次
	 * @param f
	 *            当前被访问的文件对象
	 * @return 是否深层递归，当然如果当前是文件，返回啥是无所谓的
	 */
	boolean visit(int index, RnFile f);

}
