package lifeservice.lifeservice.controller;

import lifeservice.lifeservice.entity.Merchant;
import lifeservice.lifeservice.service.ComputerService;
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
public class ComputerController {
    @Autowired
    private ComputerService computerService;
    @RequestMapping(path = "/findComputerMaintain")

    @ResponseBody
    public List<Merchant> findComputerMaintain(@RequestParam int communityId){
        return null;
    }
}
