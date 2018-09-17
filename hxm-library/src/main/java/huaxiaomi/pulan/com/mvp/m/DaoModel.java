package huaxiaomi.pulan.com.mvp.m;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

import huaxiaomi.pulan.com.HXMDaoManager;
import huaxiaomi.pulan.com.entity.DaoOpt;
import huaxiaomi.pulan.com.mvp.i.IBaseModel;
import huaxiaomi.pulan.com.mvp.i.IDaoModel;

/**
 * Description:
 * -
 * Author：chasen
 * Date： 2018/9/13 14:10
 */
public class DaoModel extends BaseModel implements IDaoModel {

    @Override
    public void save(Object o) throws DbException {
        getManager().save(o);
    }

    @Override
    public <T> void delete(Class<T> o) throws DbException {
        getManager().delete(o);
    }

    @Override
    public void update(Object o) throws DbException {
        getManager().update(o);
    }

    @Override
    public <T> List<T> findAll(Class<T> clazz) throws DbException {
        return getManager().findAll(clazz);
    }

    @Override
    public <T> List<T> findLimit(Class<T> clazz, boolean desc, String orderBy,int limit) throws DbException {
        return getManager().selector(clazz).orderBy("orderBy", desc).limit(limit).findAll();
    }

    @Override
    public <T> List<T> find(Class<T> clazz, DaoOpt opt, boolean desc) throws Exception {
        WhereBuilder whereBuilder = WhereBuilder.b(opt.getKey(),opt.getOpt(),opt.getValue());
        return getManager().selector(clazz).where(whereBuilder).orderBy("id",desc).findAll();
    }

    @Override
    public <T> List<T> find(Class<T> clazz, int from, int to,String orderBy, boolean desc) throws Exception {
        return getManager().selector(clazz).offset(from).limit(to - from).orderBy(orderBy,desc).findAll();
    }

    private DbManager getManager(){
        return HXMDaoManager.getDbManager();
    }
}
