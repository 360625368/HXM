package huaxiaomi.pulan.com.mvp.i;

import java.util.List;

import huaxiaomi.pulan.com.entity.DaoOpt;

/**
 * Description:
 * -
 * Author：chasen
 * Date： 2018/9/13 14:02
 */
public interface IDaoModel extends IBaseModel {

    void save(Object o) throws Exception;

    <T> void delete(Class<T> o) throws Exception;

    void update(Object o) throws Exception;

    <T> List<T> findAll(Class<T> clazz) throws Exception;

    <T> List<T> findLimit(Class<T> clazz, boolean desc, String orderBy, int limit) throws Exception;

    <T> List<T> find(Class<T> clazz, DaoOpt opt, boolean desc) throws Exception;

    <T> List<T> find(Class<T> clazz, int from,int to,String orderBy, boolean desc) throws Exception;
}
