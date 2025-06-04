package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.Product;
import fdz.migue.housfybackend.entity.House;
import fdz.migue.housfybackend.repository.ProductRepository;
import fdz.migue.housfybackend.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private HouseRepository houseRepository;

    @Transactional(readOnly = true)
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsByHouseId(Long houseId) {
        if (!houseRepository.existsById(houseId)) {
            throw new RuntimeException("House not found with id " + houseId);
        }
        return productRepository.findByHouse_HouseId(houseId);
    }

    @Transactional
    public Product create(Product product){
        House house = houseRepository.findById(product.getHouse().getHouseId())
                .orElseThrow(() -> new RuntimeException("House not found with id " + product.getHouse().getHouseId()));
        product.setHouse(house);
        return productRepository.save(product);
    }

    @Transactional
    public Product update(Product product){
        return productRepository.save(product);
    }

    @Transactional
    public Product updateById(Long id, Product productDetails){
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));

        foundProduct.setName(productDetails.getName());

        if (productDetails.getHouse() != null && productDetails.getHouse().getHouseId() != null) {
            House house = houseRepository.findById(productDetails.getHouse().getHouseId())
                    .orElseThrow(() -> new RuntimeException("House not found with id " + productDetails.getHouse().getHouseId()));
            foundProduct.setHouse(house);
        } else {
            throw new IllegalArgumentException("House ID cannot be null for Product update.");
        }

        return productRepository.save(foundProduct);
    }

    @Transactional
    public void delete(Long id){
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }
}