package ru.mikheev.kirill.hw13bank.dao.general;

/**
 * Interface for entities, which support transactional behaviour
 */
public interface Transactional {

    /**
     * Fix current state of entity
     */
    void fixState();

    /**
     * Save result of all operations, that was made on this object after state fixation
     */
    void commitState();

    /**
     * Rollback entity to last fixed state
     */
    void rollbackState();
}
