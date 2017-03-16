package org.n52.series.db.da;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.n52.io.response.AbstractOutput;
import org.n52.series.db.DataAccessException;
import org.n52.series.db.beans.HierarchicalEntity;
import org.n52.series.db.dao.DbQuery;

public abstract class HierarchicalParameterRepository<E extends HierarchicalEntity<E>, O extends AbstractOutput> extends SessionAwareRepository implements OutputAssembler<O> {

    protected List<O> createExpanded(Collection<E> entities, DbQuery parameters) throws DataAccessException {
        Set<O> results = new HashSet<>();
        if (entities != null) {
            for (E entity : entities) {
                O result = createExpanded(entity, parameters);
                results.add(result);
            }
        }
        return new ArrayList<>(results);
    }

    protected abstract O createExpanded(E procedureEntity, DbQuery parameters) throws DataAccessException;

    protected List<O> createCondensed(Collection<E> entities, DbQuery parameters) {
        Set<O> results = new HashSet<>();
        if (entities != null) {
            for (E entity : entities) {
                O result = createCondensed(entity, parameters);
                results.add(result);
            }
        }
        return new ArrayList<>(results);
    }

    protected abstract O createCondensed(E entity, DbQuery parameters);

}