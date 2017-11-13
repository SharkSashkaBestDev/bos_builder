package ?(servicePackage).impl;

import ?(entityPackage).?(name);
import ?(exceptionPackage).?(name)NotFoundException;
import ?(repositoryPackage).?(name)Repository;
import ?(servicePackage).?(name)Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ?(basePackage).configurator.entity.Document;
import com.csg.alexandr.grain.service.impl.NextSequenceService;

import java.util.List;

@Service
public class ?(name)ServiceImpl implements ?(name)Service {

    @Autowired
    private ?(name)Repository repository;
    @Autowired
    private NextSequenceService nextSequenceService;

    @Override
    public ?(name) add(?(name) newModel) {
        if (newModel.code.value == null && newModel.code.autonumbering) newModel.code.value = String.valueOf(nextSequenceService.getNextSequence("?(name)"));

        newModel.id = null;

        return repository.save(newModel);
    }

    @Override
    public void deleteById(String id) throws ?(name)NotFoundException {
        repository.delete(id);
    }

    @Override
    public ?(name) update(?(name) newModel) throws ?(name)NotFoundException {
        ?(name) model = repository.findOne(newModel.id);

        if (model.id == null && newModel.id != null) {
            model.id = newModel.id;
        }

        model.period = newModel.period;
        model.periodicity = newModel.periodicity;
        model.carryingOut = newModel.carryingOut;
        model.operationalCarryingOut = newModel.operationalCarryingOut;
        model.movementsDeleting = newModel.movementsDeleting;
        model.name = newModel.name;
        model.presentationField = newModel.presentationField;
        model.nameLength = newModel.nameLength;
        model.code = newModel.code;
        model.synonym = newModel.synonym;
        model.comment = newModel.comment;
        model.objectPresentation = newModel.objectPresentation;
        model.listPresentation = newModel.listPresentation;
        model.hierarchy = newModel.hierarchy;
        model.deleted = newModel.deleted;
        model.nameRequired = newModel.nameRequired;
        model.uniqueness = newModel.uniqueness;
        model.showName = newModel.showName;
        model.requisites = newModel.requisites;
        model.tableParts = newModel.tableParts;
        model.owners = newModel.owners;
        model.movementsRules = newModel.movementsRules;

        model = repository.save(model);
        return model;
    }

    @Override
    public ?(name) patch(?(name) newModel) throws ?(name)NotFoundException {
        ?(name) model = repository.findOne(newModel.id);

        if (model.id == null && newModel.id != null) {
            model.id = newModel.id;
        }
        if (newModel.period != null)
        model.period = newModel.period;
        if (newModel.periodicity != null)
        model.periodicity = newModel.periodicity;
        if (newModel.carryingOut != null)
        model.carryingOut = newModel.carryingOut;
        if (newModel.operationalCarryingOut != null)
        model.operationalCarryingOut = newModel.operationalCarryingOut;
        if (newModel.movementsDeleting != null)
        model.movementsDeleting = newModel.movementsDeleting;
        if (newModel.name != null)
        model.name = newModel.name;
        if (newModel.presentationField != null)
        model.presentationField = newModel.presentationField;
        if (newModel.nameLength != null)
        model.nameLength = newModel.nameLength;
        if (newModel.code != null)
        model.code = newModel.code;
        if (newModel.synonym != null)
        model.synonym = newModel.synonym;
        if (newModel.comment != null)
        model.comment = newModel.comment;
        if (newModel.objectPresentation != null)
        model.objectPresentation = newModel.objectPresentation;
        if (newModel.listPresentation != null)
        model.listPresentation = newModel.listPresentation;
        if (newModel.hierarchy != null)
        model.hierarchy = newModel.hierarchy;
        if (newModel.deleted != null)
        model.deleted = newModel.deleted;
        if (newModel.nameRequired != null)
        model.nameRequired = newModel.nameRequired;
        if (newModel.uniqueness != null)
        model.uniqueness = newModel.uniqueness;
        if (newModel.showName != null)
        model.showName = newModel.showName;
        if (newModel.requisites != null)
        model.requisites = newModel.requisites;
        if (newModel.tableParts != null)
        model.tableParts = newModel.tableParts;
        if (newModel.owners != null)
        model.owners = newModel.owners;
        if (newModel.movementsRules != null)
        model.movementsRules = newModel.movementsRules;


        model = repository.save(model);
        return model;
    }

    @Override
    public Document patchDelete(String id) throws ?(name)NotFoundException {
        ?(name) model = repository.findOne(id);

        if (model == null) throw new ?(name)NotFoundException("Документа ?(name) не знайдено за id: " + id);

        if (model.deleted) return model;

        model.deleted = true;

        model = repository.save(model);
        return model;
    }

    @Override
    public ?(name) findById(String id) throws ?(name)NotFoundException {
        return repository.findOne(id);
    }

    @Override
    public Page<?(name)> findAll(int page, int size) {
        return repository.findAll(new PageRequest(page, size));
    }

    @Override
    public List<?(name)> findAll() {
        return repository.findAll();
    }

    @Override
    public List<?(name)> findAllWithout1(String id) throws ?(name)NotFoundException {
        return repository.findAllWithout1(id);
    }

    @Override
    public Integer getHierarchyLength(String parentId) throws ?(name)NotFoundException {
        int levelsCount = 1;
        ?(name) currentItem = findById(parentId);
        while (currentItem.hierarchy.hierarchyParent != null) {
            currentItem = findById(currentItem.hierarchy.hierarchyParent);
            levelsCount++;
        }

        return levelsCount;
    }
}

