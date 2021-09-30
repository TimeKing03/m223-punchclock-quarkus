package ch.zli.m223.punchclock.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;

@ApplicationScoped
public class CategoryService {
    @Inject
    private EntityManager entityManager;

    public CategoryService() {
    }

    @Transactional
    public Category createCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    @SuppressWarnings("unchecked")
    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Category");
        return query.getResultList();
    }

    public Category getCategory(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Transactional
    public void delete(Long id) {
        entityManager.remove(getCategory(id));
    }

    @Transactional
    public void updateEntry(Category category) {
        entityManager.merge(category);
    }

}
