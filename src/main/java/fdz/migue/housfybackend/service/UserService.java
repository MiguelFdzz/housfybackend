package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.User;
import fdz.migue.housfybackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public User create(User user){
        return userRepository.save(user);
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public User updateById(Long id, User user){
        User foundUser =  userRepository.findById(id).orElse(null);
        if(foundUser != null){
            foundUser.setName(user.getName());
            foundUser.setState(user.getState());
            return userRepository.save(foundUser);
        } else {
            throw new RuntimeException("User with  id "+id+" not found");
        }
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
