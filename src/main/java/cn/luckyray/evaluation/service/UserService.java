package cn.luckyray.evaluation.service;

/**
 *
 * @author 杨逸林
 * @date 2019-07-08 9:02
 * @return
*/
public interface UserService {
    /**
     * 根据 ID 判断用户是否存在
     * @param id
     * @author 杨逸林
     * @date 2019-07-08 9:02
     * @return boolean
    */
    boolean userIsExist(int id);
}
