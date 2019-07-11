package lifeservice.lifeservice.controller;

import lifeservice.lifeservice.entity.Merchant;
import lifeservice.lifeservice.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/09*/
@RequestMapping(path = "/api")
@Controller
public class SupermarketController {
    @Autowired
    private SupermarketService supermarketService;
    @RequestMapping(path = "/findSupermarket")
    @ResponseBody
    public List<Merchant> findSupermarket(@RequestParam int communityId){

        return supermarketService.findSupermarket(communityId);
    }
}
