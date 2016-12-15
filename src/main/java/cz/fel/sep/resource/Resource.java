package cz.fel.sep.resource;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class Resource {

    @PersistenceContext
    @Produces
    private EntityManager em;

}
