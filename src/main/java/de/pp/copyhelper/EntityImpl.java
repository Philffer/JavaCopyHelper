package de.pp.copyhelper;

/**
 * @author Philipp Pfeiffer
 * Implementation of {@link Entity} Interface
 */
public class EntityImpl implements Entity {

    private String name;
    private Long id;
    private String doNotCopy;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NoCopy
    public String getDoNotCopy() {
        return this.doNotCopy;
    }

    public void setDoNotCopy(String doNotCopy) {
        this.doNotCopy = doNotCopy;
    }

    public String getDisplayValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" - ");
        sb.append(id.toString());
        sb.append(" - ");
        sb.append(doNotCopy);

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o.getClass().isAssignableFrom(this.getClass())
                && ((EntityImpl) o).getDisplayValue().equals(this.getDisplayValue());
    }
}
