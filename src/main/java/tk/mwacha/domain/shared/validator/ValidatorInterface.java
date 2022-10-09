package tk.mwacha.domain.shared.validator;

public interface ValidatorInterface<T> {
    void validate(T entity);
}
