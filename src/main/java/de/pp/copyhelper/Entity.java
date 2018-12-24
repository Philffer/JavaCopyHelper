package de.pp.copyhelper;

/**
 * @author Philipp Pfeiffer
 * Representing the Interface for a POJO Class for testing the CopyHelper
 */
public interface Entity {

    void setName(String name);

    String getName();

    void setId(Long id);

    Long getId();

    void setDoNotCopy(String doNotCopy);

    @NoCopy
    String getDoNotCopy();

    String getDisplayValue();

}
