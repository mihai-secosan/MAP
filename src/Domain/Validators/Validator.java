package Domain.Validators;

public interface Validator<T>
{
    /**
     *
     * @param entity is a T that may or not be valid
     * @throws ValidationException if the entity isn't valid
     */
    void validate(T entity) throws ValidationException;
}
