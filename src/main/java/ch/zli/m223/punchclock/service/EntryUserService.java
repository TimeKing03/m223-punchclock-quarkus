package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.persistence.PersistenceException;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.EntryUser;
import io.quarkus.security.Authenticated;

@ApplicationScoped
public class EntryUserService {
    @Inject
    private EntityManager entityManager;

    public EntryUserService() {
    }

    @Transactional
    public EntryUser createUser(EntryUser entryUser) {
        entityManager.persist(entryUser);
        return entryUser;
    }

    @SuppressWarnings("unchecked")
    public List<EntryUser> findAll() {
        var query = entityManager.createQuery("FROM EntryUser");
        return query.getResultList();
    }

    public EntryUser getEntryUser(Long id) {
        return entityManager.find(EntryUser.class, id);
    }

    public long getEntryUserIdByName(String username) {
        var query = entityManager.createQuery("SELECT id FROM EntryUser WHERE username = :name");
        query.setParameter("name", username);
        var result = query.getSingleResult();
        return (long)result;
    }

    public boolean getEntryUserByName(String username) {
        var query = entityManager.createQuery("SELECT isAdmin FROM EntryUser WHERE username = :name");
        query.setParameter("name", username);
        var result = query.getSingleResult();
        return (boolean)result;
    }

    @Transactional
    public void delete(Long id) {
        entityManager.remove(getEntryUser(id));
    }

    @Transactional
    public EntryUser updateEntry(EntryUser entryUser) {
        entityManager.merge(entryUser);
        return entryUser;
    }

}
