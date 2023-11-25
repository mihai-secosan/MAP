package Domain;

import java.util.Objects;


/**
 * Define a Tuple o generic type entities
 * @param <E1> - tuple first entity type
 * @param <E2> - tuple second entity type
 */
public class Tuple<E1, E2>
{
    private E1 e1;
    private E2 e2;

    public Tuple(E1 e1, E2 e2)
    {
        this.e1 = e1;
        this.e2 = e2;
    }

    /**
     *
     * @return the left element
     */
    public E1 getLeft()
    {
        return e1;
    }

    /**
     *
     * @param e1 is the new left element
     */
    public void setLeft(E1 e1)
    {
        this.e1 = e1;
    }

    /**
     *
     * @return the right element
     */
    public E2 getRight()
    {
        return e2;
    }

    /**
     *
     * @param e2 is the new right element
     */
    public void setRight(E2 e2)
    {
        this.e2 = e2;
    }

    @Override
    public String toString()
    {
        return e1 + ", " + e2;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Tuple<E1, E2> other = (Tuple<E1, E2>) obj;
        return this.e1.equals(other.e1) && this.e2.equals(other.e2);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(e1, e2);
    }
}
