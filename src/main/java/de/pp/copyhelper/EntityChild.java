package de.pp.copyhelper;


/**
 * @author Philipp Pfeiffer
 * Interface inherited from the {@link Entity} Interface
 */
public interface EntityChild extends Entity {

    String getChildSpecific();

    void setChildSpecific(String childSpecific);

}
