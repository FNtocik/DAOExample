package by.training.task.web.sort.util;

import by.training.task.entities.Language;
import by.training.task.web.sort.enums.LanguageSortOrder;

import java.util.Comparator;
import java.util.List;

public class LanguageSortUtil {

    public static List<Language> sort(List<Language> entities, LanguageSortOrder sortOrder) {
        switch (sortOrder) {
            case ID_ASC:
                entities.sort(new Comparator<Language>() {
                    @Override
                    public int compare(Language o1, Language o2) {
                        return Integer.compare(o1.getId(), o2.getId());
                    }
                });
                break;
            case ID_DESC:
                entities.sort(new Comparator<Language>() {
                    @Override
                    public int compare(Language o1, Language o2) {
                        return -1 * Integer.compare(o1.getId(), o2.getId());
                    }
                });
                break;
            case SIGNATURE_ASC:
                entities.sort(new Comparator<Language>() {
                    @Override
                    public int compare(Language o1, Language o2) {
                        return o1.getSignature().compareTo(o2.getSignature());
                    }
                });
                break;
            case SIGNATURE_DESC:
                entities.sort(new Comparator<Language>() {
                    @Override
                    public int compare(Language o1, Language o2) {
                        return -1 * o1.getSignature().compareTo(o2.getSignature());
                    }
                });
                break;
        }
        return entities;
    }
}
