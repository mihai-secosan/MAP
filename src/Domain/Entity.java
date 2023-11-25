package Domain;

import java.io.Serializable;
import java.util.Objects;

public class Entity<ID> implements Serializable
{

   // private static final long serialVersionUID = 7331115341259248461L;
    protected ID id;

    /**
     *
     * @return id
     */
    public ID getId()
    {
        return id;
    }

    /**
     *
     * @param id is the new id to be set
     */
    public void setId(ID id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Entity<?> entity))
        {
            return false;
        }
        return getId().equals(entity.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId());
    }

    @Override
    public String toString()
    {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}