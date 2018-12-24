package de.pp.copyhelper;

/**
 * @author Philipp Pfeiffer
 * Implementation of the {@link EntityChild} Interface
 */
public class EntityChildImpl extends EntityImpl implements EntityChild{

    private String childSpecific;

    public String getChildSpecific() {
        return childSpecific;
    }

    public void setChildSpecific(String childSpecific) {
        this.childSpecific = childSpecific;
    }
}
