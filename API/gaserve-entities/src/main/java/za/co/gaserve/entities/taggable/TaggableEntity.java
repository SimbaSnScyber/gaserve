package za.co.gaserve.entities.taggable;

import java.util.Map;

public class TaggableEntity implements Taggable {
    @Override
    public void setTags(Map<String, String> tags) {

    }

    @Override
    public Map<String, String> getTags() {
        return null;
    }
}

interface Taggable {

    void setTags(Map<String, String> tags);
    Map<String, String> getTags();

}
