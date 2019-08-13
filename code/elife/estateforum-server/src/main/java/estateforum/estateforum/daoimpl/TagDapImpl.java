package estateforum.estateforum.daoimpl;

import estateforum.estateforum.dao.TagDao;
import estateforum.estateforum.entity.Tag;
import estateforum.estateforum.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ztHou
 * @date 2019/08/13
 */
@Repository
public class TagDapImpl implements TagDao {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag save(Tag tag){
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    @Override
    public Boolean addTagNum(String tag){
        try{
            Tag tagAdd = tagRepository.findTagByContent(tag);
            tagAdd.setNum(tagAdd.getNum() + 1);
            tagRepository.save(tagAdd);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean reduceTagNum(String tag){
        try{
            Tag tagAdd = tagRepository.findTagByContent(tag);
            Integer num = tagAdd.getNum() - 1;
            if(num == 0){
                tagRepository.deleteTagByContent(tag);
            } else {
                tagAdd.setNum(num);
                tagRepository.save(tagAdd);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
