package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        User deletedUser = get(id);
        entityManager.remove(deletedUser);
    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("uncheked")
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public User get(int id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", id).getSingleResult();
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Transactional
    @Override
    public void update(int id, User user) {
        User updatedUser = get(id);
        updatedUser.setName(user.getName());
        updatedUser.setSurName(user.getSurName());
        updatedUser.setAge(user.getAge());
    }
}