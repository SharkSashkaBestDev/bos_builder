package ?(servicePackage);

import ?(entityPackage).?(name);
import ?(exceptionPackage).?(name)NotFoundException;
import org.springframework.data.domain.Page;
import ?(basePackage).configurator.entity.Document;

import java.util.List;

public interface ?(name)Service {

    ?(name) add(?(name) newModel);

    void deleteById(String id) throws ?(name)NotFoundException;

    ?(name) update(?(name) newModel) throws ?(name)NotFoundException;

    ?(name) patch(?(name) newModel) throws ?(name)NotFoundException;

    Document patchDelete(String id) throws ?(name)NotFoundException;

    ?(name) findById(String id) throws ?(name)NotFoundException;

    Page<?(name)> findAll(int page, int size);

    List<?(name)> findAll();

    List<?(name)> findAllWithout1(String id) throws ?(name)NotFoundException;

    Integer getHierarchyLength(String parentId) throws ?(name)NotFoundException;
}

