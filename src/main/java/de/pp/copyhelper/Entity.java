package de.pp.copyhelper;

/**
 * @author Philipp Pfeiffer
 * Representing the Interface for a POJO Class for testing the CopyHelper
 */
public interface Entity {

    String getName();

    void setName(String name);

    Long getId();

    void setId(Long id);

    @NoCopy
    String getDoNotCopy();

    void setDoNotCopy(String doNotCopy);

    String getDisplayValue();

}
