package ch.zli.m223.punchclock.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.Place;

@ApplicationScoped
public class PlaceService {
    @Inject
    private EntityManager entityManager;

    public PlaceService() {
    }

    @Transactional
    public Place createPlace(Place place) {
        entityManager.persist(place);
        return place;
    }

    @SuppressWarnings("unchecked")
    public List<Place> findAll() {
        var query = entityManager.createQuery("FROM Place");
        return query.getResultList();
    }

    public Place getPlace(Long id) {
        return entityManager.find(Place.class, id);
    }

    @Transactional
    public void delete(Long id) {
        entityManager.remove(getPlace(id));
    }

    @Transactional
    public void updatePlace(Place place) {
        entityManager.merge(place);
    }

}
