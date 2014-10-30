package grails.buildtestdata.mixin

import grails.buildtestdata.mixin.TestCriteriaBuilder;

import java.util.List;

import org.grails.datastore.gorm.GormStaticApi;
import org.grails.datastore.gorm.finders.FinderMethod;
import org.grails.datastore.mapping.core.Datastore;
import org.grails.datastore.mapping.query.api.BuildableCriteria;
import org.springframework.transaction.PlatformTransactionManager;

class TestGormStaticApi extends GormStaticApi {
	TestGormStaticApi(Class persistentClass, Datastore datastore, List<FinderMethod> finders, PlatformTransactionManager transactionManager) {
		super(persistentClass, datastore, finders, transactionManager)
	}
	BuildableCriteria createCriteria() {
		new TestCriteriaBuilder(persistentClass, datastore.currentSession)
	}
}
