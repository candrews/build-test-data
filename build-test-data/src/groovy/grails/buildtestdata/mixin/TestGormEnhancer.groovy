package grails.buildtestdata.mixin

import org.grails.datastore.gorm.GormEnhancer;
import org.grails.datastore.gorm.GormStaticApi;
import org.grails.datastore.mapping.core.Datastore;
import org.springframework.transaction.PlatformTransactionManager;

public class TestGormEnhancer extends GormEnhancer {
	public TestGormEnhancer(Datastore datastore, PlatformTransactionManager transactionManager) {
		super(datastore, transactionManager)
	}
	protected <D> GormStaticApi<D> getStaticApi(Class<D> cls) {
		return new TestGormStaticApi(cls, datastore, getFinders(), transactionManager)
	}
}
