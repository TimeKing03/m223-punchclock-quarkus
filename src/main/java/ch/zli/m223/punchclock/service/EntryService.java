package ch.zli.m223.punchclock.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;
    Logger log;

    public EntryService() {
    }

    @Transactional 
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @SuppressWarnings("unchecked")
    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry");
        return query.getResultList();
    }

    public List<Entry> findAllFromUser(Long userId) {
        var query = entityManager.createQuery("FROM Entry WHERE entryUser_id = :userid");
        query.setParameter("userid", userId);

        return query.getResultList();
    }

    public Entry getEntry(Long id) {
        return entityManager.find(Entry.class, id);
    }

    @Transactional
    public void delete(Long id) {
        entityManager.remove(getEntry(id));
    }

    @Transactional
    public void updateEntry(Entry entry) {
        entityManager.merge(entry);
    }

}
