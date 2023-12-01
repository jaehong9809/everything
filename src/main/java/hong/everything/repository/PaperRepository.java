package hong.everything.repository;

import hong.everything.domain.Paper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
//사용안함

@Repository
public class PaperRepository {
    private final Map<Long, Paper> store = new ConcurrentHashMap<>();
    private long sequence=0L;

    public Paper findById(Long id){
        return store.get(id);
    }

    public Paper save(Paper paper){
        paper.setId(sequence++);
        store.put(paper.getId(), paper);
        return paper;
    }
    public void update(Long id, Paper updatePaper){
        Paper paper=findById(id);
        paper.setWords(updatePaper.getWords());
    }
    public List<Paper> findAll(){
        List<Paper> papers = new ArrayList<>();
        for (Long aLong : store.keySet()) {
            papers.add(findById(aLong));
        }
        return papers;
    }
    public void delete(Long id){
        store.remove(id);
    }
    public void clearStore(){store.clear();}
}
