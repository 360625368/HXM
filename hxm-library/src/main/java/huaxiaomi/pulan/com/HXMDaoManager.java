package huaxiaomi.pulan.com;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Description:
 * -
 * Author：chasen
 * Date： 2018/9/13 14:12
 */
public class HXMDaoManager {

    private static DbManager dbManager;
    private static DbManager.DaoConfig daoConfig;

    public static void initDaoConfig(String dbName) {
        daoConfig = new DbManager.DaoConfig()
                .setDbName(dbName + ".db")
                .setDbVersion(1)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        // 开启WAL, 对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                });
    }

    public static DbManager getDbManager() {
        if (daoConfig == null) {
            try {
                throw new Exception("未初始化DaoConfig，请调用initDaoConfig(String dbName)方法初始化");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (dbManager == null) {
            dbManager = x.getDb(daoConfig);
        }
        return dbManager;
    }
}
