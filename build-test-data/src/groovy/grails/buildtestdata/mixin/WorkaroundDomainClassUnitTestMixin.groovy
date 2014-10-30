package grails.buildtestdata.mixin

import grails.gorm.TestCriteriaBuilder
import grails.test.mixin.domain.DomainClassUnitTestMixin
import groovy.transform.CompileStatic;

import java.util.Set;

import org.codehaus.groovy.grails.commons.GrailsDomainClass;
import org.grails.datastore.gorm.GormEnhancer;
import org.grails.datastore.gorm.GormStaticApi
import org.grails.datastore.gorm.finders.FinderMethod
import org.grails.datastore.mapping.core.Datastore
import org.grails.datastore.mapping.model.PersistentEntity;
import org.grails.datastore.mapping.query.api.BuildableCriteria
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.validation.Validator;

class WorkaroundDomainClassUnitTestMixin extends DomainClassUnitTestMixin {
	@CompileStatic
	def mockDomains(Class<?>... domainClassesToMock) {
		initialMockDomainSetup()
		Collection<PersistentEntity> entities = simpleDatastore.mappingContext.addPersistentEntities(domainClassesToMock)
		for (PersistentEntity entity in entities) {
			GrailsDomainClass domain = registerGrailsDomainClass(entity.javaClass)

			Validator validator = registerDomainClassValidator(domain)
			simpleDatastore.mappingContext.addEntityValidator(entity, validator)
		}
		def enhancer = new TestGormEnhancer(simpleDatastore, transactionManager)
		final failOnError = getFailOnError()
		enhancer.failOnError = failOnError instanceof Boolean ? failOnError : false

		initializeMappingContext()

		enhancer.enhance()
	}
}
