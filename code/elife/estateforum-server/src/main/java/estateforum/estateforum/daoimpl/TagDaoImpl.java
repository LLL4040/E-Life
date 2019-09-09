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
public class TagDaoImpl implements TagDao {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag save(Tag tag){
        return tagRepository.save(tag);
    }

    @Override
    public Boolean existsByContentAndCommunityId(String tag, Long communityId){
        return tagRepository.existsTagByContentAndCommunityId(tag, communityId);
    }

    @Override
    public Tag findTagByContentAndCommunityId(String content, Long communityId){
        return tagRepository.findTagByContentAndCommunityId(content, communityId);
    }

    @Override
    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> getAllTagsByCommunityId(Long communityId){
        return tagRepository.findAllByCommunityId(communityId);
    }

    @Override
    public Boolean addTagNum(String tag, Long communityId){
        try{
            Tag tagAdd = tagRepository.findTagByContentAndCommunityId(tag, communityId);
            tagAdd.setNum(tagAdd.getNum() + 1);
            tagRepository.save(tagAdd);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean reduceTagNum(String tag, Long communityId){
        try{
            Tag tagReduce = tagRepository.findTagByContentAndCommunityId(tag, communityId);
            int num = tagReduce.getNum() - 1;
            if(num == 0){
                tagRepository.deleteTagByContentAndCommunityId(tag, communityId);
            } else {
                tagReduce.setNum(num);
                tagRepository.save(tagReduce);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
