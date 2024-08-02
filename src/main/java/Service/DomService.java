package Service;

import Entity.Dom;

import java.util.List;

public interface DomService {
    Dom findById(Integer id);
    List<Dom> getAll();
}
