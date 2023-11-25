package Domain.Validators;

import Domain.User;

public class UserValidator implements Validator<User>
{
    /**
     *
     * @param user is a User that may or may not be valid
     * @throws ValidationException if the name of the user isn't valid
     */
    @Override
    public void validate(User user) throws ValidationException
    {
        if (!user.getName().matches("^[A-Z][a-z]*(?: [A-Z][a-z]*)*$"))
        {
            String message = "Invalid Name: " + user.getName();
            throw new ValidationException(message);
        }
    }
}
