package lpka.prj.crystallis.domain.symbol.session.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class SessionDataByStage {
    private Map<ComponentKeys, Collection<?>> data;

    public static SessionDataByStage of(Map<ComponentKeys, Collection<?>> data) {
        return new SessionDataByStage(data);
    }

    private SessionDataByStage(Map<ComponentKeys, Collection<?>> data) {
        this.data = new HashMap<>();
        this.data.putAll(data);
    }

    public <T> T getData(ComponentKeys key, T dataContainer) {
        Collection<?> objects = data.get(key);
        if (dataContainer instanceof String) {
            dataContainer = ((T) new ArrayList<>(objects).get(0));
        } else if (dataContainer instanceof Boolean) {
            dataContainer = ((T) new ArrayList<>(objects).get(0));
        } else if (dataContainer instanceof Collection) {
            ((Collection) dataContainer).addAll(objects);
        }
        return dataContainer;
    }

    public void clearAndPutNew(Map<ComponentKeys, Collection<?>> newData) {
        data.clear();
        data.putAll(newData);
    }
}
