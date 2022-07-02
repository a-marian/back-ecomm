package com.back.ecomm.configuration;

import com.back.ecomm.entity.Country;
import com.back.ecomm.entity.Product;
import com.back.ecomm.entity.ProductCategory;
import com.back.ecomm.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Type;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        disableSomeHttpMethods( config, Product.class);

        disableSomeHttpMethods( config, ProductCategory.class);

        disableSomeHttpMethods( config, Country.class);

        disableSomeHttpMethods( config, State.class);

        //Call an internal helper method
        exposeIds(config);

    }

    private void exposeIds(RepositoryRestConfiguration config){
        // Get all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        //Create an array of the entity types
        List<Class> entityClasses = entities.stream().map(Type::getJavaType)
                .collect(Collectors.toList());

        //Expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }

    //Disable http methods  for ProductCategory: DELETE, POST, PUT
    private void disableSomeHttpMethods(RepositoryRestConfiguration config, Class classToConfig){

        HttpMethod[] unsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
        config.getExposureConfiguration()
                .forDomainType(classToConfig)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));
    }
}
