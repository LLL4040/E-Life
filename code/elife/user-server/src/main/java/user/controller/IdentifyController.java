package user.controller;

import org.springframework.web.bind.annotation.*;
import user.service.IdentifyService;

/**
 * @author ztHou
 */
@RequestMapping(path = "/api/user")
@RestController
public class IdentifyController {
    private final IdentifyService identifyService;

    public IdentifyController(IdentifyService identifyService) {
        this.identifyService = identifyService;
    }

    @RequestMapping(path = "/sendIdentify")
    @ResponseBody
    public Boolean sendIdentify(@RequestParam String phone) {
        return identifyService.sendIdentifyCode(phone);
    }

    @RequestMapping(path = "/verifyIdentify")
    @ResponseBody
    public Boolean verifyIdentify(@RequestParam String phone, @RequestParam String code) {
        return identifyService.verifyIdentifyCode(phone, code);
    }
}
