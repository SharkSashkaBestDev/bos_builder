package ?(repositoryPackage);

import ?(entityPackage).?(name);
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ?(name)Repository extends MongoRepository<?(name), String> {

    @Query("{ 'name' : ?0  }")
    List<?(name)> findByName(String name);

    @Query("{ '_id' : { $ne : ?0 }  }")
    List<?(name)> findAllWithout1(String id);

    @Query(value = "{ 'hierarchy.hierarchyParent' : ?0 }")
    List<?(name)> getHierarchyLength(String hierarchyParent);

    @Override
    @Query("{ 'deleted' : false }")
    Page<?(name)> findAll(Pageable pageable);

    @Override
    @Query("{ 'deleted' : false }")
    List<?(name)> findAll();
}

