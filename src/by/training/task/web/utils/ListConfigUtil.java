package by.training.task.web.utils;

import java.util.List;

public class ListConfigUtil {

    static public List getPartOfList(List entities, int fromIndex, int count) {
        if (fromIndex >= entities.size()) {
            return null;
        }
        if (fromIndex + count >= entities.size()) {
            entities = entities.subList(fromIndex, entities.size());
        } else {
            entities = entities.subList(fromIndex, fromIndex + count);
        }
        return entities;
    }
}
