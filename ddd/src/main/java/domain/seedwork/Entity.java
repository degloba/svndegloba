package domain.seedwork;

import java.io.Serializable;

/**
 * Domain entities interface. All entity classes must implement this interface either directly or indirectly . 
 * It is mainly from the tag role , in order to unify the processing system entities .
 * @author yyang (<a href="mailto:gdyangyu@gmail.com">gdyangyu@gmail.com</a>)
 * 
 */
public interface Entity extends Serializable {

	/**
	 * 取得实体的Id。实体类的每个实例都必须有个唯一Id以标识自身。
	 * 实体Id必须是可序列化的。
	 * @return 实体实例的 Id.
	 */
	Serializable getId();
	
	/**
	 * 是否在数据库中已经存在
	 * @return 如果该实体以存在于数据库中，返回true，否则返回false
	 */
	boolean existed();
	
	/**
	 * 是否在数据库中不存在
     * @return 如果该实体以存在于数据库中，返回false，否则返回true
	 */
	boolean notExisted();
}
