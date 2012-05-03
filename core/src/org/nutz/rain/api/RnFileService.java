package org.nutz.rain.api;

import java.io.InputStream;
import java.io.Reader;
import java.util.List;

/**
 * 操作文件接口
 * <p>
 * 本接口负责提供文件读取，目录结构的改变，并触发对应的钩子
 * <p>
 * 文件的全路径格式为 "~/a/b/c.txt" 如果 "~" 则表示主目录
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public interface RnFileService {

	/**
	 * 解压一个文件
	 * <p>
	 * 解压后得文件，将保证都有 .fobj 文件作为索引描述
	 * 
	 * @param dest
	 *            目标目录全路径，如果为 null 则解压在 zip 所在目录
	 * @param zip
	 *            压缩文件，如果是非压缩文件，则跳过
	 * @return
	 */
	void unzip(RnFile dest, RnFile zip);

	/**
	 * 将给定的文件压缩
	 * <p>
	 * 压缩的文件的 entity 描述基于 base
	 * <ul>
	 * <li>所有要被压缩文件，如果是目录递归
	 * <li>所有的 .fobj 文件与主文件一起被加入压缩包
	 * </ul>
	 * 
	 * @param base
	 *            压缩的基路径，如果为 null 则取 fs 最小父目录为基
	 * @param filter
	 *            一个过滤器，来说明要压缩哪些文件
	 * @param fs
	 *            要被压缩的文件
	 * @return
	 */
	RnFile zip(RnFile base, RnFileFilter filter, RnFile... fs);

	/**
	 * @param fs
	 *            文件对象列表
	 * @return 本组文件最小父目录
	 */
	RnFile commonParent(RnFile... fs);

	/**
	 * TODO 看看 jgit 的相关接口先 ...
	 * 
	 * @return 当前库，所有需要改变的文件对象
	 */
	List<RnFile> listStatus();

	/**
	 * 根据一个全路径获得一个文件对象
	 * 
	 * @param path
	 *            全路径格式为 "~/a/b/c.txt"
	 * @return 文件对象
	 */
	RnFile get(String path);

	/**
	 * 访问给定文件或者目录
	 * <ul>
	 * <li>第一个被访问的，一定是给定的 f
	 * <li>进行深度优先的递归
	 * <li>访问器 visitor 可以控制是否继续深层递归
	 * </ul>
	 * 
	 * @param f
	 *            要被访问的目录或者文件
	 * @param visitor
	 *            文件访问器
	 */
	void visit(RnFile f, RnFileVisitor visitor);

	/**
	 * 将当前给定的文件的修改，统统保存到历史记录中
	 * <p>
	 * 如果给的文件列表为空，则表示提交全部有修改的文件列表
	 * <p>
	 * 每次提交，都会为 rev 增加 1，并记录对应的指纹
	 * 
	 * @param fs
	 *            文件列表
	 */
	void commit(RnFile... fs);

	/**
	 * 移动一个文件对象<br>
	 * 同时，它也会移动文件的索引信息到新的位置
	 * <p>
	 * 本操作也可用户给文件改名
	 * 
	 * @param f
	 *            文件对象
	 * @param dest
	 *            一个全路径，格式为 "~/a/b/c.txt"
	 */
	void move(RnFile f, String dest);

	/**
	 * 删除一个文件对象<br>
	 * 同时，它也会删除文件的索引信息
	 * 
	 * @param f
	 *            文件对象
	 */
	void delete(RnFile f);

	/**
	 * 创建一个目录
	 * <p>
	 * 如果目录存在，跳过。如果存在同名文件，则抛错
	 * 
	 * @param path
	 *            根据一个路径，创建一个目录
	 */
	RnFile mkdirIfNoExists(String path);

	/**
	 * 创建一个文件
	 * <p>
	 * 如果文件存在，跳过。如果存在同名目录，则抛错
	 * 
	 * @param path
	 *            文件路径
	 * @param ftype
	 *            文件类型
	 * @return
	 */
	RnFile mkfileIfNoExists(String path, String ftype);

	/**
	 * 更新一个文件对象的索引信息
	 * 
	 * @param f
	 *            文件对象
	 */
	void update(RnFile f);

	/**
	 * 按二进制方式读取文件内容流
	 * 
	 * @param f
	 *            文件对象
	 * @param rev
	 *            文件版本，-1 表示文件所在工作目录的版本
	 * @return 二进制流
	 */
	InputStream readBinary(RnFile f, long rev);

	/**
	 * 按文本方式读取文件内容流
	 * 
	 * @param f
	 *            文件对象
	 * @param rev
	 *            文件版本，-1 表示文件所在工作目录的版本
	 * @return 文件文本内容
	 */
	String readText(RnFile f, long rev);

	/**
	 * 覆写一个文件的内容，本修改仅仅修改工作区文件，并不保存进历史记录<br>
	 * 同时，它也会更新文件的索引信息
	 * 
	 * @param f
	 *            文件对象
	 * @param ins
	 *            二进制流
	 */
	void writeBinary(RnFile f, InputStream ins);

	/**
	 * 覆写一个文件的内容，本修改仅仅修改工作区文件，并不保存进历史记录<br>
	 * 同时，它也会更新文件的索引信息
	 * 
	 * @param f
	 *            文件对象
	 * @param bs
	 *            二进制内容
	 */
	void writeBinary(RnFile f, byte[] bs);

	/**
	 * 覆写一个文件的内容，本修改仅仅修改工作区文件，并不保存进历史记录<br>
	 * 同时，它也会更新文件的索引信息
	 * 
	 * @param f
	 *            文件对象
	 * @param text
	 *            文本内容
	 */
	void writeText(RnFile f, Reader reader);

	/**
	 * 覆写一个文件的内容，本修改仅仅修改工作区文件，并不保存进历史记录<br>
	 * 同时，它也会更新文件的索引信息
	 * 
	 * @param f
	 *            文件对象
	 * @param text
	 *            文本内容
	 */
	void writeText(RnFile f, String text);

	/**
	 * 追加内容至文件末尾，本修改仅仅修改工作区文件，并不保存进历史记录<br>
	 * 它不会更新文件的索引信息
	 * 
	 * @param f
	 *            文件对象
	 * @param text
	 *            文本内容
	 */
	void appendText(RnFile f, Reader reader);

	/**
	 * 追加内容至文件末尾，本修改仅仅修改工作区文件，并不保存进历史记录<br>
	 * 它不会更新文件的索引信息
	 * 
	 * @param f
	 *            文件对象
	 * @param text
	 *            文本内容
	 */
	void appendText(RnFile f, String text);

}
