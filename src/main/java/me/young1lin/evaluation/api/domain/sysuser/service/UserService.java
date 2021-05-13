package me.young1lin.evaluation.api.domain.sysuser.service;

/**
 *
 * @author 杨逸林
 * @date 2019-07-08 9:02
*/
public interface UserService {

    /**
     * 根据 ID 判断用户是否存在
     * @param id 用户id
     * @author 杨逸林
     * @date 2019-07-08 9:02
     * @return boolean
    */
    boolean userIsExist(int id);

}
