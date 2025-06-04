package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.House;
import fdz.migue.housfybackend.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Transactional(readOnly = true)
    public List<House> findAll(){
        return houseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<House> findById(Long id){
        return houseRepository.findById(id);
    }

    @Transactional
    public House create(House house){
        return houseRepository.save(house);
    }

    @Transactional
    public House save(House house){
        return houseRepository.save(house);
    }

    @Transactional
    public House updateById(Long id, House houseDetails){
        House foundHouse = houseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("House with id " + id + " not found"));

        foundHouse.setName(houseDetails.getName());

        return houseRepository.save(foundHouse);
    }

    @Transactional
    public void delete(Long id){
        if (!houseRepository.existsById(id)) {
            throw new RuntimeException("House with id " + id + " not found");
        }
        houseRepository.deleteById(id);
    }
}
