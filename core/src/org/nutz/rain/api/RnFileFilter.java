package org.nutz.rain.api;

/**
 * 一个文件过滤器
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public interface RnFileFilter {

	/**
	 * @param f
	 *            文件对象
	 *            
	 * @return true 接受， false 不接受
	 */
	boolean accept(RnFile f);

}
