package com.amathur.userdirectory.controller;

import com.amathur.userdirectory.exception.UserException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
@Validated
public class UserController
{
    // LMAO deprecated
    @Deprecated
    @GetMapping("/")
    public ModelAndView getUserHome() throws UserException
    {
//        ModelAndView modelAndView = new ModelAndView("user-home");
//        modelAndView.addObject("users", new UserGetController().findAllUsers());
//        return modelAndView;
        return null;
    }
}
