package www.zhaoxinkeji.com.dbdatabase.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import www.zhaoxinkeji.com.dbdatabase.entity.ProductEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.AffairEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.DistributorEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.StorageRoomEntity;
import www.zhaoxinkeji.com.dbdatabase.entity.BillRuleEntity;

import www.zhaoxinkeji.com.dbdatabase.greendao.ProductEntityDao;
import www.zhaoxinkeji.com.dbdatabase.greendao.AffairEntityDao;
import www.zhaoxinkeji.com.dbdatabase.greendao.DistributorEntityDao;
import www.zhaoxinkeji.com.dbdatabase.greendao.StorageRoomEntityDao;
import www.zhaoxinkeji.com.dbdatabase.greendao.BillRuleEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig productEntityDaoConfig;
    private final DaoConfig affairEntityDaoConfig;
    private final DaoConfig distributorEntityDaoConfig;
    private final DaoConfig storageRoomEntityDaoConfig;
    private final DaoConfig billRuleEntityDaoConfig;

    private final ProductEntityDao productEntityDao;
    private final AffairEntityDao affairEntityDao;
    private final DistributorEntityDao distributorEntityDao;
    private final StorageRoomEntityDao storageRoomEntityDao;
    private final BillRuleEntityDao billRuleEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        productEntityDaoConfig = daoConfigMap.get(ProductEntityDao.class).clone();
        productEntityDaoConfig.initIdentityScope(type);

        affairEntityDaoConfig = daoConfigMap.get(AffairEntityDao.class).clone();
        affairEntityDaoConfig.initIdentityScope(type);

        distributorEntityDaoConfig = daoConfigMap.get(DistributorEntityDao.class).clone();
        distributorEntityDaoConfig.initIdentityScope(type);

        storageRoomEntityDaoConfig = daoConfigMap.get(StorageRoomEntityDao.class).clone();
        storageRoomEntityDaoConfig.initIdentityScope(type);

        billRuleEntityDaoConfig = daoConfigMap.get(BillRuleEntityDao.class).clone();
        billRuleEntityDaoConfig.initIdentityScope(type);

        productEntityDao = new ProductEntityDao(productEntityDaoConfig, this);
        affairEntityDao = new AffairEntityDao(affairEntityDaoConfig, this);
        distributorEntityDao = new DistributorEntityDao(distributorEntityDaoConfig, this);
        storageRoomEntityDao = new StorageRoomEntityDao(storageRoomEntityDaoConfig, this);
        billRuleEntityDao = new BillRuleEntityDao(billRuleEntityDaoConfig, this);

        registerDao(ProductEntity.class, productEntityDao);
        registerDao(AffairEntity.class, affairEntityDao);
        registerDao(DistributorEntity.class, distributorEntityDao);
        registerDao(StorageRoomEntity.class, storageRoomEntityDao);
        registerDao(BillRuleEntity.class, billRuleEntityDao);
    }
    
    public void clear() {
        productEntityDaoConfig.clearIdentityScope();
        affairEntityDaoConfig.clearIdentityScope();
        distributorEntityDaoConfig.clearIdentityScope();
        storageRoomEntityDaoConfig.clearIdentityScope();
        billRuleEntityDaoConfig.clearIdentityScope();
    }

    public ProductEntityDao getProductEntityDao() {
        return productEntityDao;
    }

    public AffairEntityDao getAffairEntityDao() {
        return affairEntityDao;
    }

    public DistributorEntityDao getDistributorEntityDao() {
        return distributorEntityDao;
    }

    public StorageRoomEntityDao getStorageRoomEntityDao() {
        return storageRoomEntityDao;
    }

    public BillRuleEntityDao getBillRuleEntityDao() {
        return billRuleEntityDao;
    }

}
