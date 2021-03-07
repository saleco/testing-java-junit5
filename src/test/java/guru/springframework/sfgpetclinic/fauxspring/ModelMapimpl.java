package guru.springframework.sfgpetclinic.fauxspring;

import java.util.HashMap;
import java.util.Map;

public class ModelMapimpl implements Model {

    private Map<String, Object> map;

    public ModelMapimpl() {
        map = new HashMap<>();
    }

    @Override
    public void addAttribute(String key, Object o) {
        map.put(key, o);
    }

    @Override
    public void addAttribute(Object o) {
        //do nothing
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
